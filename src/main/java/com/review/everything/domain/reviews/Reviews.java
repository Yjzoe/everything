package com.review.everything.domain.reviews;

import com.review.everything.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Reviews extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String title;
    private String writer;
    private String content;
    private int gpa;
    private String img;
    private String oneSentence;

    @Builder
    public Reviews(String category,String title,String writer,String content,String img,String oneSentence,int gpa) {
        this.category = category;
        this.title = title;
        this.category = content;
        this.writer = writer;
        this.oneSentence = oneSentence;
        this.img = img;
        this.gpa = gpa;
    }

    public void update(String category,String title,String content,String img,String oneSentence,int gpa) {
        this.category = category;
        this.title = title;
        this.category = content;
        this.oneSentence = oneSentence;
        this.img = img;
        this.gpa = gpa;
    }

}
