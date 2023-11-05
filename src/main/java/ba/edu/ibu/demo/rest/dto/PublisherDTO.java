package ba.edu.ibu.demo.rest.dto;


import ba.edu.ibu.demo.core.model.Publisher;

public class PublisherDTO {
    private String id;
    private String name;
    private String email;


    public PublisherDTO() {}

    public PublisherDTO(Publisher publisher) {
        this.id = publisher.getId();
        this.name = publisher.getName();
        this.email = publisher.getEmail();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
