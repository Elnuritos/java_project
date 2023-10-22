package ba.edu.ibu.demo.rest.dto;

import ba.edu.ibu.demo.core.model.Author;

public class AuthorRequestDTO {
    private String name;
    private String nationality;


    public AuthorRequestDTO() {}

    public AuthorRequestDTO(Author author) {
        this.name = author.getName();
        this.nationality = author.getNationality();
    }

public Author toEntity(){
        Author author=new Author();
        author.setName(name);
        author.setNationality(nationality);
        return  author;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}

