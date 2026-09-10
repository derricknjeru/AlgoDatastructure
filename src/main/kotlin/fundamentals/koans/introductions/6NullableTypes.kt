package fundamentals.koans.introductions

fun main() {
    /**
     * https://kotlinlang.org/docs/null-safety.html
     *
     * Learn about null safety and safe calls in Kotlin and rewrite the following Java code so that it only has one if expression:
     * ​
     * `
     `
     * public void sendMessageToClient(
     *     @Nullable Client client,
     *     @Nullable String message,
     *     @NotNull Mailer mailer
     * ) {
     *     if (client == null || message == null) return;
     *
     *     PersonalInfo personalInfo = client.getPersonalInfo();
     *     if (personalInfo == null) return;
     *
     *     String email = personalInfo.getEmail();
     *     if (email == null) return;
     *
     *     mailer.sendMessage(email, message);
     * } `
     *`
     *fun sendMessageToClient(
     *         client: Client?, message: String?, mailer: Mailer
     * ) {
     *     TODO()
     * }
     *
     * class Client(val personalInfo: PersonalInfo?)
     * class PersonalInfo(val email: String?)
     * interface Mailer {
     *     fun sendMessage(email: String, message: String)
     * }
     */
}

fun sendMessageToClient(
    client: Client?, message: String?, mailer: Mailer
) {
   val email = client?.personalInfo?.email
   if (email != null && message != null) {
       mailer.sendMessage(email, message)
   }
}

class Client(val personalInfo: PersonalInfo?)
class PersonalInfo(val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}