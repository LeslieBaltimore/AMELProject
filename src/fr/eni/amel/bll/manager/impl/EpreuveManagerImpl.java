package fr.eni.amel.bll.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import fr.eni.amel.bll.manager.EpreuveManager;
import fr.eni.amel.bo.Epreuve;
import fr.eni.amel.bo.Question;
import fr.eni.amel.bo.SectionTest;
import fr.eni.amel.bo.Test;
import fr.eni.amel.dal.EpreuveDAO;
import fr.eni.amel.dal.SectionTestDao;
import fr.eni.amel.dal.factory.DaoFactory;
import fr.eni.tp.web.common.bll.exception.ElementNotFoundException;
import fr.eni.tp.web.common.bll.exception.ManagerException;
import fr.eni.tp.web.common.dal.exception.DaoException;
import fr.eni.tp.web.common.util.ValidationUtil;

public class EpreuveManagerImpl implements EpreuveManager {

	private static EpreuveManagerImpl instance;
	private EpreuveDAO epreuveDAO = DaoFactory.epreuveDAO();
	private SectionTestDao sectionTestDAO = DaoFactory.getSectionTestDao();

	private EpreuveManagerImpl() {

	}

	public static EpreuveManagerImpl getInstance() {
		if (instance == null) {
			instance = new EpreuveManagerImpl();
		}
		return instance;
	}

	/**
	 * lorqu'un candidat est authentifi�, lister les �preuves auxquels il est
	 * inscrit
	 */
	@Override
	public List<Epreuve> listerEpreuvesPourUtilisateur(Integer idUtilisateur, Boolean seulement_active) throws ManagerException {
		
		List<Epreuve> listeEpreuvesUtilisateur = null;		
		try {
			ValidationUtil.checkNotNull(idUtilisateur);
			listeEpreuvesUtilisateur = epreuveDAO.selectByUtilisateur(idUtilisateur);

		} catch (DaoException e) {
			throw new ManagerException("Message Manager", e);
		}
		
		
		if(seulement_active)
		{
			
			Iterator<Epreuve> iter = listeEpreuvesUtilisateur.iterator();

			while (iter.hasNext()) {
				Epreuve epreuve = iter.next();
				
				if(!DateManagerImpl.isBeforeDay(new Date(), epreuve.getDateFinValidite()) || !DateManagerImpl.isAfterDay(epreuve.getDateFinValidite(), new Date()))
				{
					iter.remove();
				}
			}
		}

		return listeEpreuvesUtilisateur;
	}
	
	public Epreuve getUneEpreuve(Integer idEpreuve) {
		Epreuve epreuve = null;

		try {
			epreuve = epreuveDAO.selectById(idEpreuve);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return epreuve;
	}

	/**
	 * Pour une �preuve s�lectionn�e, r�cup�rer pour le test , les sections
	 * tests associ�es avec le nombre de questions � tirer et le th�me des
	 * questions
	 * 
	 * @return
	 */
	@Override
	public List<SectionTest> listerSectionsTestsPourEpreuve(Integer idEpreuve) throws ManagerException {
		List<SectionTest> listeSectionTest = null;
		try {
			ValidationUtil.checkNotNull(idEpreuve);

			listeSectionTest = epreuveDAO.selectById(idEpreuve).getTest().getListeSectionTests();
		} catch (DaoException e) {
			throw new ManagerException("Message Manager", e);
		}
		return listeSectionTest;
	}
	
	
	public void updateTimer(int timer, int idEpreuve) {
		
		try {
			Epreuve epreuve = epreuveDAO.selectById(idEpreuve);
			epreuve.setTempsEcoule(epreuve.getTest().getDuree() - (int)Math.floor(timer / 60));
			epreuveDAO.update(epreuve);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addTimer(int idEpreuve) {
		
		try {
			Epreuve epreuve = epreuveDAO.selectById(idEpreuve);
			epreuve.setTempsEcoule(epreuve.getTempsEcoule() + 1);
			epreuveDAO.update(epreuve);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	/**
	 * Pour une sectionTest, r�cup�rer les questions
	 */
	@Override
	public List<Question> tirerAuSortQuestions(Integer idEpreuve) throws ManagerException {
		ValidationUtil.checkNotNull(idEpreuve);
		List<Question> listeQuestionsEpreuve = new ArrayList();
	
			for (SectionTest section : this.listerSectionsTestsPourEpreuve(idEpreuve)) {
				// Pour une sectionTest, r�cup�rer le nombre de questions �
				// tirer
				long nbQuestionsATirer = section.getNbQuestionsATirer();

				// Pour une sectionTest, r�cup�rer la liste de questions du
				// th�me
				List<Question> listeQuestions = section.getTheme().getListeQuestions();

				// Initialisation du nombre de questions tir�es
				long nbQuestionsTirees = 0;
				Question questionTiree = null;

				// Tant que le nb de questions tir�es par section
				// est inf�rieur au nb de questions � tirer par section
				// on tire un nb al�atoire compris entre 0 et la taille de la
				// liste
				// de questions
				// on r�cup�re la question tir�e, on l'ajoute � la liste de
				// questions de l'�preuve
				// on retire la question de la liste de questions du th�me pour
				// �tre
				// s�r qu'elle ne sorte
				// pas en double
				while (nbQuestionsTirees < nbQuestionsATirer) {

					Random RANDOM = new Random();

					if (!listeQuestions.isEmpty()) {
						Integer rand = RANDOM.nextInt(listeQuestions.size());
						questionTiree = listeQuestions.get(rand);
						nbQuestionsTirees = nbQuestionsTirees + 1;
						listeQuestionsEpreuve.add(questionTiree);
						System.out.println(questionTiree);
						listeQuestions.remove(questionTiree);
					} else {
						break;
					}
				}
			}
		
		return listeQuestionsEpreuve;
	}

	@Override
	public Epreuve insert(Epreuve epreuve) throws ManagerException {
		try {
			epreuve = epreuveDAO.insert(epreuve);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return epreuve;
	}
}
