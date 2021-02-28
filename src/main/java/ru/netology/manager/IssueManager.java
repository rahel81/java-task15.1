package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.domain.NewIssueComparator;
import ru.netology.repository.IssueRepository;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class IssueManager {
    IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue item) {
        repository.save(item);
    }

    public void addAll(Collection<Issue> items) {
        repository.saveAll(items);
    }

    public Collection<Issue> getAll() {
        return repository.findAll();
    }

    public Collection<Issue> showOpenIssue() {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (item.isOpen()) {
                result.add(item);
            }
        }
        return result;
    }

    public Collection<Issue> showCloseIssue() {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (!item.isOpen()) {
                result.add(item);
            }
        }
        return result;
    }

    public Collection<Issue> filterByAuthor(Predicate<String> predicate) {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (predicate.test(item.getAuthor())) {
                result.add(item);
            }
        }
        return result;
    }

    public Collection<Issue> filterByLabel(Predicate<HashSet<String>> predicate) {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (predicate.test(item.getLabel())) {
                result.add(item);
            }
        }
        return result;
    }

    public Collection<Issue> filterByAssignee(Predicate<HashSet<String>> predicate) {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (predicate.test(item.getAssignee())) {
                result.add(item);
            }
        }
        return result;
    }
        public Collection<Issue> sortByDataNewIssue(Comparator<Issue> newIssue) {
        ArrayList<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            result.add(item);
        }
        Collections.sort(result, newIssue);
        return result;
    }


        public Collection<Issue> sortByDataOldIssue () {
            Comparator newIssue = Comparator.reverseOrder();
            ArrayList<Issue> result = new ArrayList<>();
            for (Issue item : repository.findAll()) {
                result.add(item);
            }
            Collections.sort(result, newIssue);
            return result;
        }

        public void closeById ( int id){
            for (Issue item : repository.findAll()) {
                if (item.getId() == id && item.isOpen()) {
                    item.status(false);
                }
            }
        }

        public void openById ( int id){
            for (Issue item : repository.findAll()) {
                if (item.getId() == id && !item.isOpen()) {
                    item.status(true);
                }
            }
        }
    }
