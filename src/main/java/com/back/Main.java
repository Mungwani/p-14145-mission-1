package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Quote {
    int id;
    String content;
    String author;

    Quote(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);
        List<Quote> quotes = new ArrayList<>();

        int lastId = 0;

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료"))
                break;
            else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine();
                System.out.print("작가 : ");
                String author = scanner.nextLine();

                int id = ++lastId;
                Quote quote = new Quote(id, content, author);
                quotes.add(quote);
                System.out.println(id + "번 명언이 등록되었습니다.");

            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언\n----------------------");
                for (int i = quotes.size() - 1; i >= 0; i--) {
                    Quote quote = quotes.get(i);
                    System.out.println(quote.id + " / " + quote.author + " / " + quote.content);
                }
            } else if (cmd.startsWith("삭제?id=")) {
                String[] parts = cmd.split("=");
                int deleteId = Integer.parseInt(parts[1]);

                boolean isFound = false;

                for (int i = 0; i < quotes.size(); i++) {
                    Quote quote = quotes.get(i);
                    if (quote.id == deleteId) {
                        quotes.remove(i);
                        isFound = true;
                        break;
                    }
                }

                if (isFound) {
                    System.out.println(deleteId + "번 명언이 삭제되었습니다.");
                } else {
                    System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
                }

            } else if (cmd.startsWith("수정?id=")) {
                String[] parts = cmd.split("=");
                int editId = Integer.parseInt(parts[1]);

                Quote found = null;

                for (int i = 0; i < quotes.size(); i++) {
                    Quote quote = quotes.get(i);
                    if (quote.id == editId) {
                        found = quote;
                        break;
                    }
                }

                if (found == null) {
                    System.out.println(editId + "번 명언은 존재하지 않습니다.");
                } else {
                    System.out.println("명언(기존) : " + found.content);
                    System.out.print("명언 : ");
                    found.content = scanner.nextLine();

                    System.out.println("작가(기존) : " + found.author);
                    System.out.print("작가 : ");
                    found.author = scanner.nextLine();
                }
            }

        }
    }

}
