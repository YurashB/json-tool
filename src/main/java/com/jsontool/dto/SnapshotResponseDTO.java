package com.jsontool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SnapshotResponseDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime lastModified;


    public static final class SnapshotResponseDTOBuilder {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime lastModified;

        private SnapshotResponseDTOBuilder() {
        }

        public static SnapshotResponseDTOBuilder SnapshotResponseDTO() {
            return new SnapshotResponseDTOBuilder();
        }

        public SnapshotResponseDTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public SnapshotResponseDTOBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public SnapshotResponseDTOBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public SnapshotResponseDTOBuilder withLastModified(LocalDateTime lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public SnapshotResponseDTO build() {
            return new SnapshotResponseDTO(id, title, content, lastModified);
        }
    }
}
