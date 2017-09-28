package com.testing.system.entities;

import com.testing.system.dao.annotations.Column;
import com.testing.system.dao.annotations.Id;
import com.testing.system.dao.annotations.Table;

/**
 * Created by yuri on 19.09.17.
 */
@Table("question_options")
public class QuestionOption implements Identified<Long> {

        @Id
        private Long id;

        @Column("text")
        private String text;

        @Column("is_right")
        private Boolean isRight;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getText() {
                return text;
        }

        public void setText(String text) {
                this.text = text;
        }

        public Boolean getRight() {
                return isRight;
        }

        public void setRight(Boolean right) {
                isRight = right;
        }

        @Override
        public String toString() {
                return "QuestionOption{" +
                        "id=" + id +
                        ", text='" + text + '\'' +
                        ", isRight=" + isRight +
                        '}';
        }
}
