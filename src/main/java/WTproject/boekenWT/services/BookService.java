package WTproject.boekenWT.services;

import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import WTproject.boekenWT.models.*;
import WTproject.boekenWT.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import WTproject.boekenWT.repositories.AuthorRepository;
import WTproject.boekenWT.repositories.BookRepository;

@Component
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CategoryRepository categoryRepository;

    //ADD
    public String addBook(BookDTO bookTemplate) {

        Book book = new Book();
        Set<Author> authors = new HashSet<Author>();
        Set<Category> categories = new HashSet<Category>();
        Year year = bookTemplate.getYear();

        Set<Author> newAuthors = bookTemplate.getAuthor();
        Set<Category> newCategories = bookTemplate.getCategories();
        Book newBook = bookTemplate.getBook();


        // Check if the author already exists
        for(Author newAuthor: newAuthors) {
            Author author = new Author();
            if (authorRepository.existsById(newAuthor.getAuthorId())) {
                // Fetch the existing author from the database
                author = authorRepository.findById(newAuthor.getAuthorId()).get();
                authors.add(author);
            } else {
                // Create and save the new author
                author.setName(newAuthor.getName());
                try {
                    author = authorRepository.save(author);
                    authors.add(author);

                    // Save and get the managed entity
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            }
        }
        // Check if the category already exists
        for(Category newCategory: newCategories) {
            Category category = new Category();
            if (categoryRepository.existsById(newCategory.getCategoryId())) {
                // Fetch the existing category from the database
                category = categoryRepository.findById(newCategory.getCategoryId()).get();
                categories.add(category);
            } else {
                // Create and save the new category
                category.setCategory(newCategory.getCategory());
                try {
                    category = categoryRepository.save(category);
                    categories.add(category);

                    // Save and get the managed entity
                } catch (Exception e) {
                    return "Error: " + e.getMessage();
                }
            }
        }

        // Check if the book already exists
        if(bookRepository.existsById(newBook.getIsbn())) {
            return "Book already exists";
        }
        else {
            try {
                book.setIsbn(newBook.getIsbn());
                book.setTitle(newBook.getTitle());
                for(Author newAuthor: authors) {
                    book.addAuthor(newAuthor);
                }
                for(Category newCategory : categories) {
                    book.addCategory(newCategory);
                }
                book.setYear(year);
                book.setIsOnline(newBook.getIsOnline());
                book.setIsPhysical(newBook.getIsPhysical());
                book.setSummary(newBook.getSummary());

                bookRepository.save(book);
            } catch (Exception e) {
                return "ErrorBS: " + e;
            }
        }

        return "Book added";
    }

    //GET
    public String getBook(int isbn) {
        if(bookRepository.existsById(isbn)) {
            Book book = bookRepository.findById(isbn).get();
            return "Book found, " + book.getTitle() + ", with isbn: " +  isbn; //Nog even nadenken over wat we hier daadwerkelijk willen returnen.
        }
        else {
            return "Book not found";
        }
    }

    public List<Book> getAllBooks() {
        return (List<Book>)bookRepository.findAll();
    }

    //UPDATE
    public String updateBookData(BookDTO bookTemplate) {
        Set<Author> authors = new HashSet<Author>();
        Set<Category> categories = new HashSet<Category>();

        int isbn = bookTemplate.getBook().getIsbn();
        Year newYear = bookTemplate.getYear();
        Set<Author> newAuthors = bookTemplate.getAuthor();
        Set<Category> newCategories = bookTemplate.getCategories();
        Book newBook = bookTemplate.getBook();

        if(bookRepository.existsById(isbn)) {

            // Check if the author already exists
            for(Author newAuthor: newAuthors) {
                Author author = new Author();
                if (authorRepository.existsById(newAuthor.getAuthorId())) {
                    // Fetch the existing author from the database
                    author = authorRepository.findById(newAuthor.getAuthorId()).get();
                    authors.add(author);
                } else {
                    // Create and save the new author
                    author.setName(newAuthor.getName());
                    try {
                        author = authorRepository.save(author);
                        authors.add(author);

                        // Save and get the managed entity
                    } catch (Exception e) {
                        return "Error: " + e.getMessage();
                    }
                }
            }
            // Check if the category already exists
            for(Category newCategory: newCategories) {
                Category category = new Category();
                if (categoryRepository.existsById(newCategory.getCategoryId())) {
                    // Fetch the existing category from the database
                    category = categoryRepository.findById(newCategory.getCategoryId()).get();
                    categories.add(category);
                } else {
                    // Create and save the new category
                    category.setCategory(newCategory.getCategory());
                    try {
                        category = categoryRepository.save(category);
                        categories.add(category);

                        // Save and get the managed entity
                    } catch (Exception e) {
                        return "Error: " + e.getMessage();
                    }
                }
            }

            Book updatedBook = bookRepository.findById(isbn).get();
            try {
                updatedBook.setIsbn(newBook.getIsbn());
                updatedBook.setTitle(newBook.getTitle());
                for(Author newAuthor: authors) {
                    updatedBook.addAuthor(newAuthor);
                }
                for(Category newCategory : categories) {
                    updatedBook.addCategory(newCategory);
                }
                updatedBook.setYear(newYear);
                updatedBook.setIsOnline(newBook.getIsOnline());
                updatedBook.setIsPhysical(newBook.getIsPhysical());
                updatedBook.setSummary(newBook.getSummary());

                bookRepository.save(updatedBook);
            } catch (Exception e) {
                return "ErrorBS: " + e;
            }


            // Check if the author already exists
//            for(Author newAuthor: newAuthors) {
//                Author author = new Author();
//                if (authorRepository.existsById(newAuthor.getAuthorId())) {
//                    // Fetch the existing author from the database
//                    author = authorRepository.findById(newAuthor.getAuthorId()).get();
//                    authors.add(author);
//                } else {
//                    // Create and save the new author
//                    author.setName(newAuthor.getName());
//                    try {
//                        author = authorRepository.save(author);
//                        authors.add(author);
//
//                        // Save and get the managed entity
//                    } catch (Exception e) {
//                        return "Error: " + e.getMessage();
//                    }
//                }
//            }
        } else {
            return "This book does not yet exist, update failed.";
        }
//        Book updatedBook = bookRepository.findById(book.getIsbn()).get();
//        updatedBook.setTitle(book.getTitle());
//        updatedBook.setYear(book.getYear());
//
//        // if (book.getAuthors().size() > 0) {
//        //     for (int i = 0; i < book.getAuthors().size(); i++) {
//        //         //if author doesn't exist, add it
//        //         if (updatedBook.getAuthors().iterator().next().getAuthorId() != book.getAuthors().iterator().next().getAuthorId()) {
//        //             updatedBook.addAuthor(book.getAuthors().iterator().next());
//        //         }
//        //     }
//        // }
//        Object[] authorArray = book.getAuthors().toArray();
//
//        for(int i=0; i < authorArray.length; i++) {
//            Author author = (Author) authorArray[i];
//            System.out.println("SECOND TEST:" + author);
//        }
//
//        try {
//            bookRepository.save(updatedBook);
//        } catch (Exception e) {
//            return "Error: " + e;
//        }
        return "book id " + bookTemplate.getBook()+ " updated.";
    }

    //DELETE
    public String deleteBookItem(int isbn) {
        if (bookRepository.existsById(isbn)) {
            try {
                bookRepository.deleteById(isbn);
                //Nog doen: ook de authors van dit boek moeten verwijderd worden uit the authorrepository als ze niet gekoppeld zijn aan een ander boek.
                return "Book deleted";
            } catch (Exception e) {
                return "Error: " + e;
            }
        }
        else {
            return "Book not found";
        }
    }
}
