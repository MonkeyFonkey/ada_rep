public class CelebrityProblem {
    public static int findCelebrity(int[][] party) {
        int n = party.length;
        // Pick the first person as a candidate
        int celebrity = 0;

        // Check if celebrity knows anyone, if yes then update celebrity
        for (int i = 1; i < n; i++) {
            if (party[celebrity][i] == 1) {
                celebrity = i;
            }
        }

        // Check if the candidate is a celebrity
        for (int i = 0; i < n; i++) {
            // Skip checking the candidate with itself
            if (i == celebrity) {
                continue;
            }

            // If the candidate knows anyone, or someone doesn't know the candidate, then the candidate is not a celebrity
            if (party[celebrity][i] == 1 || party[i][celebrity] == 0) {
                return -1;
            }
        }

        return celebrity;
    }

    public static void main(String[] args) {
        int[][] party = {
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0},
                {0, 0, 1, 0}
        };

        int celebrityIndex = findCelebrity(party);

        if (celebrityIndex == -1) {
            System.out.println("No celebrity found");
        } else {
            System.out.println("Celebrity found at index " + celebrityIndex);
        }
    }
}
