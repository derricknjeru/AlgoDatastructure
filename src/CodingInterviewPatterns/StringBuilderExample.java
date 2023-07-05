package CodingInterviewPatterns;

class StringBuilderExample {
    public static void main(String[] args) {
        String s = "Derrick Njeru";

        StringBuilder sb = new StringBuilder();
        sb.append(s);

        System.out.println(s.indexOf("D"));
        System.out.println(sb.indexOf("D"));

        System.out.println(sb.delete(0,1));
        System.out.println(sb.deleteCharAt(0));

        System.out.println(sb.insert(1,"John"));

        System.out.println(sb.length());

        sb.append('c'); //append character

    }
}