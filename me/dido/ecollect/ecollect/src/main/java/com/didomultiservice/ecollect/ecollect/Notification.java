package com.didomultiservice.ecollect.ecollect;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @ColumnDefault("nextval('notifications_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date_prevue")
    private OffsetDateTime datePrevue;

    @Column(name = "date_envoye")
    private OffsetDateTime dateEnvoye;

    @Column(name = "message", length = Integer.MAX_VALUE)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('notifications_type_notification_id_seq')")
    @JoinColumn(name = "type_notification_id", nullable = false)
    private com.didomultiservice.ecollect.ecollect.TypeNotification typeNotification;

    @Column(name = "vue")
    private Boolean vue;

    @Column(name = "contact", nullable = false, length = Integer.MAX_VALUE)
    private String contact;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @ColumnDefault("nextval('notifications_bien_id_seq')")
    @JoinColumn(name = "bien_id", nullable = false)
    private Bien bien;

}