package Grind75.week5;

import java.util.*;

public class AccountMerge {
    //https://www.youtube.com/watch?v=QHniHFvxAl8&t=20s
    static class Solution {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            int accountsCount = accounts.size();
            UnionFindByRank uf = new UnionFindByRank(accountsCount);

            Map<String, Integer> emailToAccountIdx = new HashMap<>();
            for (int i = 0; i < accountsCount; i++) {
                List<String> account = accounts.get(i);
                for (int j = 1; j < account.size(); j++) {
                    String email = account.get(j);
                    if (emailToAccountIdx.containsKey(email)) {
                        uf.union(i, emailToAccountIdx.get(email));
                    } else {
                        emailToAccountIdx.put(email, i);
                    }
                }
            }

            List<List<String>> mergedAccounts = new ArrayList<>();
            Map<Integer, Integer> groupIdx = new HashMap<>();
            int nextIdx = 0;
            for (Map.Entry<String, Integer> entry : emailToAccountIdx.entrySet()) {
                String email = entry.getKey();
                int groupId = uf.find(entry.getValue());
                if (groupIdx.containsKey(groupId)) {
                    int idx = groupIdx.get(groupId);
                    mergedAccounts.get(idx).add(email);
                } else {
                    groupIdx.put(groupId, nextIdx);
                    List<String> newGroup = new ArrayList<>();
                    newGroup.add(accounts.get(groupId).get(0)); // Name
                    newGroup.add(email);
                    mergedAccounts.add(newGroup);
                    nextIdx++;
                }
            }

            // Sort the email lists within mergedAccounts (excluding the name)
            for (List<String> account : mergedAccounts) {
                if (account.size() > 2) { // Excluding name
                    Collections.sort(account.subList(1, account.size()));
                }
            }

            return mergedAccounts;
        }
    }

    public static void main(String[] args) {
        // Example accounts
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "john@example.com", "johnsmith@example.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@example.com"));
        accounts.add(Arrays.asList("John", "john@example.com", "john00@example.com"));
        accounts.add(Arrays.asList("Mary", "mary@example.com"));

        Solution solution = new Solution();
        List<List<String>> mergedAccounts = solution.accountsMerge(accounts);

        // Print merged accounts
        for (List<String> account : mergedAccounts) {
            System.out.println(account);
        }
    }


}
