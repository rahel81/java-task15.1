package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
//import ru.netology.domain.IssueComparatorReverse;
import ru.netology.domain.NewIssueComparator;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);
    NewIssueComparator newIssue = new NewIssueComparator();
    Issue first = new Issue(1, "Петров", new HashSet<String>(Arrays.asList("Build")), new HashSet<String>(Arrays.asList("rahel")), true, 3);
    Issue second = new Issue(2, "Иванов", new HashSet<String>(Arrays.asList("Reporting")), new HashSet<String>(Arrays.asList("britter")), false, 1);
    Issue third = new Issue(3, "Федоров", new HashSet<String>(Arrays.asList("Bug")), new HashSet<String>(Arrays.asList("dstaff")), true, 5);

    @BeforeEach
    void setUp() {
        repository.addAll(List.of(first, second, third));
    }

    @Test
    public void shouldAddNewIssue() {
        Issue fourth = new Issue(4, "Козлов", new HashSet<String>(Arrays.asList("Task")), new HashSet<String>(Arrays.asList("XX")), false, 10);
        manager.add(fourth);
        List<Issue> expected = List.of(first, second, third, fourth);
        List<Issue> actual = repository.returnAll();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldOpenIssue() {
        Collection<Issue> expected = List.of(first, third);
        Collection<Issue> actual = manager.showOpenIssue();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCloseIssue() {
        Collection<Issue> expected = List.of(second);
        Collection<Issue> actual = manager.showCloseIssue();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByAuthor() {
        String author = "Иванов";
        Predicate<String> equalAuthor = t -> t.equals(author);
        Collection<Issue> expected = List.of(second);
        Collection<Issue> actual = manager.filterByAuthor(equalAuthor);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByLabel() {
        Set<String> label = new HashSet<>(Arrays.asList("Build"));
        Predicate<HashSet<String>> equalLabel = t -> t.equals(label);
        Collection<Issue> expected = List.of(first);
        Collection<Issue> actual = manager.filterByLabel(equalLabel);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByAssignee() {
        Set<String> assignee = new HashSet<>(Arrays.asList("dstaff"));
        Predicate<HashSet<String>> equalAssignee = t -> t.equals(assignee);
        Collection<Issue> expected = List.of(third);
        Collection<Issue> actual = manager.filterByAssignee(equalAssignee);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSortByNewIssue() {
        Collection<Issue> expected = List.of(second, first, third);
        Collection<Issue> actual = manager.sortByDataNewIssue(newIssue);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSortByOldIssue() {
        Collection<Issue> expected = List.of(third, first, second);
        Collection<Issue> actual = manager.sortByDataOldIssue();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldByIdOpenIssue() {
        Collection<Issue> expected = List.of(first, third);
        Collection<Issue> actual = manager.showOpenIssue();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldByIdCloseIssue() {
        Collection<Issue> expected = List.of(second);
        Collection<Issue> actual = manager.showCloseIssue();
        assertEquals(expected, actual);
    }
}

