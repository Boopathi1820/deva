import static spark.Spark.*;

import java.util.ArrayList;

class Book {
    int id;
    String title;
    String author;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}

public class LibraryWeb {

    static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {

        port(80);

        get("/", (req, res) -> {
            String html = "<h1>Library Management System</h1>";
            html += "<form action='/add' method='post'>";
            html += "ID: <input name='id'/><br>";
            html += "Title: <input name='title'/><br>";
            html += "Author: <input name='author'/><br>";
            html += "<button type='submit'>Add Book</button>";
            html += "</form><br>";

            html += "<h2>Books</h2>";

            for (Book b : books) {
                html += "<p>" + b.id + " - " + b.title + " by " + b.author + "</p>";
            }

            return html;
        });

        post("/add", (req, res) -> {
            int id = Integer.parseInt(req.queryParams("id"));
            String title = req.queryParams("title");
            String author = req.queryParams("author");

            books.add(new Book(id, title, author));

            res.redirect("/");
            return null;
        });
    }
}