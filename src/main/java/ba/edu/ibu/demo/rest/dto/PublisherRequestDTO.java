package ba.edu.ibu.demo.rest.dto;

import ba.edu.ibu.demo.core.model.Author;
import ba.edu.ibu.demo.core.model.Publisher;

public class PublisherRequestDTO {
    private String name;
    private String country;
    private String email;


    public PublisherRequestDTO() {}

    public PublisherRequestDTO(Publisher publisher) {
        this.name = publisher.getName();
        this.country = publisher.getCountry();
        this.email = publisher.getEmail();
    }
    public Publisher toEntity(){
        Publisher publisher=new Publisher();
        publisher.setName(name);
        publisher.setCountry(country);
        publisher.setEmail(email);
        return  publisher;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

