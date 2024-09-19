package WTproject.boekenWT.services;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import WTproject.boekenWT.models.*;
import WTproject.boekenWT.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PhysicalBookRepository physicalBookRepository;
    @Autowired
    PhysicalBookCopyRepository physicalBookCopyRepository;
    @Autowired
    PhysicalConditionRepository physicalConditionRepository;

    //ADD
    public String addBook(BookDTO bookTemplate) {

        Book book = new Book();
        Year year = bookTemplate.getYear();

        Set<Author> newAuthors = bookTemplate.getAuthor();
        Set<Category> newCategories = bookTemplate.getCategories();
        Book newBook = bookTemplate.getBook();


        Set<Author> authors = addAuthorsForBook(newAuthors);
        Set<Category> categories = addCategoriesForBook(newCategories);


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
    public List<PhysicalBookCopy> getBookInfo(int isbn) {
        List<PhysicalBookCopy> physicalBookCopies = new ArrayList<>();
        if(bookRepository.existsById(isbn)) {
            return physicalBookCopyRepository.findCopiesByIsbn(isbn);
        }
        else {
            return physicalBookCopies;
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
        } else {
            return "This book does not yet exist, update failed.";
        }

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

    public Set<Author> addAuthorsForBook(Set<Author> newAuthors) {
        Set<Author> authors = new HashSet<>();

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
                    System.out.println("Error: " + e);
                    return authors; // Return empty set
                }
            }
        }
        return authors;
    }

    public Set<Category> addCategoriesForBook(Set<Category> newCategories) {
        Set<Category> categories = new HashSet<>();
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
                    System.out.println("Error: " + e);
                    return categories; // Return empty set
                }
            }
        }
        return categories;
    }

    public List<CatalogDTO> getAllCatalogData() {
        Iterable<Book> books=bookRepository.findAll();

        List<CatalogDTO> listCatalog=new ArrayList();
        for (Book b:books) {
            CatalogDTO cd = new CatalogDTO(b);
            listCatalog.add(cd);
        }
        return listCatalog;
    }
}
