package com.epam.controller;


import com.epam.model.Employee;
import com.epam.model.Issue;
import com.epam.repository.IssueRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/issue")
public class IssueController {

    private static final Logger logger = Logger.getLogger(IssueController.class);

    @Autowired
    IssueRepository issueRepository;

    @GetMapping("/allissues")
    public List<Issue> getAllIssues() {
        logger.info("IssueController getAllIssues called");
        return issueRepository.findAll();
    }

    @GetMapping(value="getissuebyid/{issueId}")
    public Issue getIssueById (@PathVariable Long issueId) {
        logger.info("IssueController getIssueById called");
        Optional<Issue> issue = issueRepository.findById(issueId);

        if (!issue.isPresent())
            throw new RuntimeException(" Issue id " + issueId + " not found");

        return issue.get();
    }

    @DeleteMapping(value="/deleteissuebyid/{issueId}")
    public Issue deleteIssueIdeById(@PathVariable Long issueId) {
        logger.info("IssueController.deleteIssueIdeById called");
        Optional<Issue> issue = issueRepository.findById(issueId);
        issueRepository.deleteById(issueId);
        if (!issue.isPresent())
            throw new RuntimeException(" Issue id " + issueId + " not found");

        return issue.get();
    }

    @PostMapping(value="/addissue")
    public Issue addIssue(@RequestBody Issue issue) {
        logger.info("IssueController.addIssue called");
        Issue newIssue = issueRepository.save(issue);
        return newIssue;
    }

    @PutMapping(value="/updateissue/{issueId}")
    public Issue updateIssue(@PathVariable Long issueId, @RequestBody Issue issue) {
        logger.info("IssueController.updateIssue called");
        Optional<Issue> optionalIssue = issueRepository.findById(issueId);
        if (optionalIssue.isPresent()) {
            Issue issueToUpdate = optionalIssue.get();
            issueToUpdate.setDescription(issue.getDescription());
            issueToUpdate.setUrgency(issue.getUrgency());
            issueRepository.save(issueToUpdate);
            return issueToUpdate;
        }
        else {
            throw new RuntimeException(" Issue id " + issueId + " not found");
        }

    }
}
