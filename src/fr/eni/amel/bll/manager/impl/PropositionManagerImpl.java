package fr.eni.amel.bll.manager.impl;

import java.util.ArrayList;
import java.util.List;

import fr.eni.amel.bll.manager.PropositionManager;
import fr.eni.amel.bo.Epreuve;
import fr.eni.amel.bo.Proposition;
import fr.eni.amel.bo.QuestionTirage;
import fr.eni.amel.dal.PromotionDao;
import fr.eni.amel.dal.factory.DaoFactory;
import fr.eni.tp.web.common.dal.exception.DaoException;

public class PropositionManagerImpl implements PropositionManager {

	private static PropositionManagerImpl instance;

	public static PropositionManagerImpl getInstance() {
		if (instance == null) {
			instance = new PropositionManagerImpl();
		}
		return instance;
	}

	public void Repondre(QuestionTirage questiontirage, List<Proposition> newlisteproposition) {

		List<Proposition> oldproposition = questiontirage.getProposition();

		for (Proposition uneProposition : oldproposition) {

			Boolean existe = false;

			for (Proposition laProposition : newlisteproposition) {

				if (uneProposition.getIdProposition() == laProposition.getIdProposition()) {
					existe = true;
				}

			}

			if (!existe) {
				try {
					DaoFactory.questiontirageDAO().deleteReponse(questiontirage.getEpreuve().getIdEpreuve(),
							questiontirage.getQuestion().getIdQuestion(), uneProposition.getIdProposition());
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		for (Proposition laProposition : newlisteproposition) {

			Boolean existe = false;

			for (Proposition uneProposition : oldproposition) {

				if (uneProposition.getIdProposition() == laProposition.getIdProposition()) {
					existe = true;
				}

			}

			if (!existe) {
				try {
					DaoFactory.questiontirageDAO().createReponse(questiontirage.getEpreuve().getIdEpreuve(),
							questiontirage.getQuestion().getIdQuestion(), laProposition.getIdProposition());
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	public List<Proposition> getReponseCochee(Integer idEpreuve, Integer idQuestion) {
		List<Proposition> listeReponse = new ArrayList();

		try {
			listeReponse = DaoFactory.propositionDAO().selectReponseByEpreuveQuestion(idEpreuve, idQuestion);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listeReponse;
	}

	public List<Proposition> getPropositionsBonnesParQuestion(Integer idQuestion) {
		List<Proposition> listeReponsesBonnes = new ArrayList();

		try {
			listeReponsesBonnes = DaoFactory.propositionDAO().selectPropositionsBonnesParQuestion(idQuestion);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listeReponsesBonnes;

	}

}
