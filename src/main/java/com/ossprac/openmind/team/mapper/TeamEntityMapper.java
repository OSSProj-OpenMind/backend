package com.ossprac.openmind.team.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ossprac.openmind.lecture.entity.Lecture;
import com.ossprac.openmind.lecture.repository.LectureRepository;
import com.ossprac.openmind.team.dto.req.TeamCreateRequest;
import com.ossprac.openmind.team.dto.req.TeamInvitationRequest;
import com.ossprac.openmind.team.entity.Team;
import com.ossprac.openmind.team.entity.UserTeam;
import com.ossprac.openmind.team.repository.TeamRepository;
import com.ossprac.openmind.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TeamEntityMapper {
	private final LectureRepository lectureRepository;
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	public Team toTeamEntity(TeamCreateRequest request) {
		Lecture lecture = lectureRepository.findById(request.getLectureId()).orElseThrow();
		String name = request.getTeamName();
		return TeamCreateRequest.toEntity(lecture, name);
	}

	public List<UserTeam> toUserTeamEntity(TeamInvitationRequest request) {
		Team team = teamRepository.findById(request.getTeamId()).orElseThrow();
		List<UserTeam> userTeams = createUserTeam(team, request.getMembersId());
		return userTeams;
	}

	private List<UserTeam> createUserTeam(Team team, List<Long> membersId) {
		return membersId.stream()
			.map(id -> userRepository.findById(id).orElseThrow())
			.map(user -> TeamInvitationRequest.toEntity(team, user))
			.collect(Collectors.toList());
	}
}
