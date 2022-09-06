package com.tgt.upcurve.ImageAPI.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images" )
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonAlias("id")
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name="image_code",length=10000)
    @JsonProperty("image_code")
    @JsonAlias("imageCode")
    private byte[] imageCode;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    @JsonAlias("createdAt")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now(ZoneId.of("UTC"));

    }


}
