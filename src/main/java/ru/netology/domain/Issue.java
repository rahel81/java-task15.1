package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Issue implements Comparable<Issue> {
    private int id;
    private String author;
    private HashSet<String> label;
    private HashSet<String> assignee;
    private boolean open;
    private int day;

    public void status(boolean status) {
        open = status;
    }

    @Override
    public int compareTo(Issue o) {
        return this.getDay() - o.getDay();
    }
}
