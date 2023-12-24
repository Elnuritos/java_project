package ba.edu.ibu.demo.rest.dto;

import ba.edu.ibu.demo.core.model.Publication;

public class PublicationDTO {
    private String id;
    private String title;
    private String content;

    public PublicationDTO(Publication publication) {
        this.id = publication.getId();
        this.title = publication.getTitle();
        this.content = publication.getContent();
    }



    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }


    public void setContent(String content) {
        this.content = content;
    }
}
