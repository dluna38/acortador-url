package co.acortador.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Entity
@Table(name = "Link")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "original_url")
    private String originalUrl;
    @Column(unique = true)
    private String code;
    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_links_usuario"))
    private Usuario usuario;
    @ColumnDefault("1")
    @Builder.Default
    private boolean state=true;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime validUntil;

    @Transient
    @Builder.Default
    private Long validDays = 30L;

    @PrePersist
    private void insertValidUntil(){
        if(validUntil == null){
            this.validUntil = (LocalDateTime.now()).plusDays(validDays).withHour(23)
                    .withMinute(59)
                    .withSecond(59)
                    .withNano(0);
        }
    }

}
