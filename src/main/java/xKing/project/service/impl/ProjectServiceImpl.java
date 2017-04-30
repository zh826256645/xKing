package xKing.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xKing.branch.domain.Branch;
import xKing.branch.domain.BranchMember;
import xKing.exception.AbsentException;
import xKing.exception.ExistedException;
import xKing.exception.FaultyOperationException;
import xKing.project.dao.ProjectRepository;
import xKing.project.domain.Project;
import xKing.project.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	
	// 获取组织的项目
	@Override
	public Project getProject(Branch currentBranch, String projectName) {
		Project project = projectRepository.findByProjectNameAndBranch_id(projectName, currentBranch.getId());
		if(project == null) {
			throw new AbsentException("该项目不存在！");
		}
		return project;
	}
	
	// 创建 project
	@Override
	public Project createProject(Branch currentBranch, BranchMember currentMember, String projectName) {
		Project project = projectRepository.findByProjectNameAndBranch_id(projectName, currentBranch.getId());
		if(projectName == null) {
			throw new FaultyOperationException("项目名不能为空！");
		}
		if(project != null) {
			throw new ExistedException("该项目名已经被使用！");
		}
		Project newProject = new Project();
		newProject.init(currentBranch, currentMember, projectName);
		return projectRepository.save(newProject);
	}

	// 获取组织的项目
	@Override
	public Page<Project> getProjects(Branch currentBranch, Pageable pageable) {
		return projectRepository.findByBranch_idOrderByCreateTimeDesc(currentBranch.getId(), pageable);
	}

}
