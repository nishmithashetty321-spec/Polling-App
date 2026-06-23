package com.voting.votingApp.service;

import com.voting.votingApp.model.OptionVote;
import com.voting.votingApp.model.Poll;
import com.voting.votingApp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    @Autowired
    private PollRepository pollRepository;

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id) {
        return pollRepository.findById(id);
    }

    public void vote(Long pollId, int optionIndex) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("poll not found"));
        List<OptionVote>  options= poll.getOptions();
        if(optionIndex<options.size() &&  optionIndex>0) {
            OptionVote selectedOption=options.get(optionIndex-1);
            selectedOption.setVoteCount(selectedOption.getVoteCount()+1);
            pollRepository.save(poll);
        }
        else{
            throw new IllegalArgumentException("option not found");
        }



    }
}
