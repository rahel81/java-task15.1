package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueRepository {
    private List<Issue> items = new ArrayList<>();

    public void save(Issue item) {
        items.add(item);
    }

    public void saveAll(Collection<Issue> items) {
        items.addAll(items);
    }

    public Collection<Issue> findAll() {
        return items;
    }

    public Issue findById(int id) {
        for (Issue item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public List<Issue> returnAll() {
        return items;
    }

    public void openIssue(int id) {
        if (findById(id) != null) {
            for (Issue item : items) {
                if (item.getId() == id) {
                    item.status(false);
                }
            }
        }
    }

      public void closeIssue(int id) {
        if (findById(id) != null) {
            for (Issue item : items) {
                if (item.getId() == id) {
                    item.status(true);
                }
            }
        }
    }

    public void openById(int id) {
        for (Issue item : items) {
            if (item.getId() == id && !item.isOpen()) {
                item.status(true);
            }
        }
    }

    public boolean add(List<Issue> items) {
        return items.addAll(items);
    }

    public boolean addAll(Collection<Issue> items) {
        return this.items.addAll(items);
    }

    public void removeById(int id) {
        items.removeIf(issues -> issues.getId() == id);
    }

    public boolean removeAll(Collection<Issue> items) {
        return this.items.removeAll(items);
    }
}
