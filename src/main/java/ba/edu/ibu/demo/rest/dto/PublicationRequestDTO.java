package ba.edu.ibu.demo.rest.dto;


import ba.edu.ibu.demo.core.model.Publication;

public class PublicationRequestDTO {
    private String title;
    private String content;


    public Publication toEntity() {
        Publication publication = new Publication();
        publication.setTitle(this.title);
        publication.setContent(this.content);
        return publication;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
