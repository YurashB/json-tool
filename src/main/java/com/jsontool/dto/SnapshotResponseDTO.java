package com.jsontool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class SnapshotResponseDTO {

    private Long id;
    private String title;
    private String content;
    private Long userId;


    public static final class SnapshotResponseDTOBuilder {
        private Long id;
        private String title;
        private String content;
        private Long userId;

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

        public SnapshotResponseDTOBuilder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public SnapshotResponseDTO build() {
            return new SnapshotResponseDTO(id, title, content, userId);
        }
    }
}
