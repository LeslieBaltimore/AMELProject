package fr.eni.amel.dal;

import java.util.List;

import fr.eni.amel.bo.Question;

import fr.eni.tp.web.common.dal.dao.GenericDAO;

public interface QuestionDao extends GenericDAO<Question, Integer>{

	List<Question> selectAllByTheme(Integer id);
}
