# Problem 2 – Conversation service (and conversation history)

> **Priority: HIGH.** Core Intercom domain: a conversation between one customer, Fin (the AI agent) and
> teammates. Also covers "Design a conversation history system" (pagination).

| File | Purpose |
|------|---------|
| [`practice/Practice.kt`](practice/Practice.kt) | Skeleton + runnable examples. |
| [`solution/Solution.kt`](solution/Solution.kt) | Reference with inboxes, notes, leaving, cursor pagination. |
| `src/test/kotlin/intercom/_2conversationservice/` | Unit tests. |

```bash
./gradlew -q runMain -PmainClass=intercom._2conversationservice.practice.PracticeKt
./gradlew -q runMain -PmainClass=intercom._2conversationservice.solution.SolutionKt
```

---

## 1. Prompt

> Build the in-memory backend for Intercom conversations. A conversation is started by a **customer**.
> **Fin**, our AI agent, is a participant from the start. Human **employees** can join mid-conversation.
> Only participants can send messages, and every message must be delivered to the other participants.

```kotlin
class ConversationService {
    fun createConversation(customerId: String): String
    fun addParticipant(conversationId: String, employeeId: String)
    fun sendMessage(conversationId: String, senderId: String, message: String)
    fun getMessages(conversationId: String, limit: Int): List<Message>
}
```

### Example

```text
id = createConversation("cust-42")                      -> "conv-1"   participants: cust-42, fin
sendMessage(id, "cust-42", "Hello, I need help with my payment.")   #1
sendMessage(id, "fin",     "I can help you with that.")             #2
addParticipant(id, "alice")
sendMessage(id, "alice",   "Hi, I'm Alice from Support. Let me look into this.")   #3
addParticipant(id, "bob")
sendMessage(id, "bob",     "Bob from Billing here - I found the duplicate charge.") #4

getMessages(id, 10) -> #1, #2, #3, #4  (chronological)
getMessages(id, 2)  -> #3 (alice), #4 (bob)

Inboxes (delivered = sent by someone else while you were a participant):
  cust-42 -> #2 #3 #4      fin -> #1 #3 #4      alice -> #4      bob -> (none)

sendMessage(id, "eve", "hi") -> IllegalArgumentException (not a participant)
```

---

## 2. Suggested timeline (55 min)

| Time | Phase |
|------|-------|
| 0–5 | Clarify roles, delivery semantics, ordering, error handling. |
| 5–12 | Model: `Conversation(id, customerId, participants, messages)`, `Message(id, seq, sender, text)`, `inboxes`. |
| 12–30 | Implement create / add / send / getMessages. |
| 30–35 | Run the example; test non-participant send. |
| 35–45 | Follow-ups: leave, internal notes, history pagination. |
| 45–55 | Scale: persistence, real-time delivery, ordering across servers. |

---

## 3. Clarifying questions

1. Can a conversation have more than one customer? *(No – one customer, many employees.)*
2. Is Fin always present? Can Fin be removed (hand-off to human)? *(Present from the start.)*
3. Does an employee who joins late see earlier messages? *(Yes in history, but they're not "delivered" retroactively.)*
4. Does the sender get their own message in their inbox? *(No.)*
5. What's `getMessages(limit)` – oldest or latest N? Order? *(Latest N, chronological.)*
6. Error behaviour for unknown conversation / non-participant? *(Throw.)*
7. Can participants leave? Can the customer leave? *(Employees yes; customer no.)*
8. Private notes / group vs private messages? *(Intercom has internal notes: teammates only.)*
9. Thread-safety / persistence in scope? *(In-memory, single-threaded first; discuss later.)*

---

## 4. Progressive hints

1. Separate the **log** (per-conversation append-only list – source of truth for history) from **delivery** (per-participant inbox).
2. Give each message a per-conversation `sequence` = `messages.size + 1`. It gives total order and makes `sequence - 1` the list index.
3. "Latest N in chronological order" = `messages.takeLast(n)` – or walk backwards if you need to skip hidden messages.
4. Store roles (`CUSTOMER`, `AI_AGENT`, `EMPLOYEE`) so you can enforce rules (customer can't leave or write notes).
5. Keep "everyone who ever joined" separate from "currently active" so people who left can still read history.
6. Pagination: a cursor is just a `sequence`. `before=s` → indices `[s-1-pageSize, s-1)`.

---

## 5. Complexity (reference solution)

| Operation | Time |
|-----------|------|
| `createConversation`, `addParticipant`, `removeParticipant` | O(1) |
| `sendMessage` | O(p) fan-out |
| `getMessages(limit)` | O(limit) (+ skipped notes for the customer view) |
| `getHistory(pageSize, before)` | O(pageSize) |
| Space | O(m) log + O(m·p) inbox references |

---

## 6. Follow-ups

| Follow-up | Strong answer |
|-----------|---------------|
| Participants leaving | Remove from active set; keep in `roles` so they can still read history; no further delivery. |
| Private vs group / internal notes | `Visibility.INTERNAL_NOTE`: delivered to teammates and Fin, filtered out of the customer view. |
| Conversation history at scale (Example 4) | Store messages in a table keyed `(conversation_id, sequence)`; cursor pagination `WHERE seq < ? ORDER BY seq DESC LIMIT ?` – never `OFFSET`. Archive old conversations to cold storage. |
| Ordering with many servers | Assign `sequence` in one place per conversation (DB row lock / single partition in Kafka keyed by conversation id). Client timestamps are untrusted. |
| Real-time delivery | Persist first, then publish to a pub/sub; WebSocket gateways push to connected clients; offline users get push/email. Inbox = "unread" pointers instead of copies. |
| Fan-out cost with huge groups | Fan-out on read (store once, each user keeps a `lastReadSequence`) instead of fan-out on write. |
| Exactly-once / retries | Client-generated message id as idempotency key; dedupe on insert. |
| Edits / deletes | Event-sourced: append `MessageEdited` events; soft-delete. |
| Read receipts / unread counts | Per participant `lastReadSequence`; unread = `latestSeq - lastReadSequence`. |
| Concurrency | Lock per conversation (or actor per conversation) so sequence assignment + fan-out are atomic. |

---

## 7. Self-review checklist

- [ ] Example output matches, including inboxes.
- [ ] Non-participants and people who left cannot send.
- [ ] `getMessages(limit)` returns the **latest** N in **chronological** order.
- [ ] Explained log vs inbox and fan-out-on-write vs on-read.
