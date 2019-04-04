package com.example.demofour.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

@Document(indexName = "mkyong", type = "books")
public class Book {
 
    @Id
	@Field(fielddata = true)
    private String id;
    @Field(fielddata = true)
    private String title;
    private String author;
    private String releaseDate;
    private Date  creatTime;
 
    public Book() {
    }
 
    public Book(String id, String title, String author, String releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
    }
 
 
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setCreatTime(Date creatTime){
    	this.creatTime = creatTime;
	}
	public Date getCreatTime(){
    	return creatTime;
	}
	@Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
				", releaseDate='" + creatTime + '\'' +
                '}';
    }
}
