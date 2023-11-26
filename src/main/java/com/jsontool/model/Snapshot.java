package com.jsontool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "snapshots")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime lastModified;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public static final class SnapshotBuilder {
        private String title;
        private String content;
        private LocalDateTime lastModified;
        private User user;

        private SnapshotBuilder() {
        }

        public static SnapshotBuilder Snapshot() {
            return new SnapshotBuilder();
        }

        public SnapshotBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public SnapshotBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public SnapshotBuilder withLastModified(LocalDateTime lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public SnapshotBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public Snapshot build() {
            Snapshot snapshot = new Snapshot();
            snapshot.setTitle(title);
            snapshot.setContent(content);
            snapshot.setLastModified(lastModified);
            snapshot.setUser(user);
            return snapshot;
        }
    }
}
