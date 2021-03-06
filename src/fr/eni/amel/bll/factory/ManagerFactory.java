package fr.eni.amel.bll.factory;

import fr.eni.amel.bll.manager.EpreuveManager;
import fr.eni.amel.bll.manager.ProfilManager;
import fr.eni.amel.bll.manager.PromotionManager;
import fr.eni.amel.bll.manager.PropositionManager;
import fr.eni.amel.bll.manager.QuestionManager;
import fr.eni.amel.bll.manager.QuestionTirageManager;
import fr.eni.amel.bll.manager.TestManager;
import fr.eni.amel.bll.manager.UtilisateurManager;
import fr.eni.amel.bll.manager.impl.EpreuveManagerImpl;
import fr.eni.amel.bll.manager.impl.ProfilManagerImpl;
import fr.eni.amel.bll.manager.impl.PromotionManagerImpl;
import fr.eni.amel.bll.manager.impl.PropositionManagerImpl;
import fr.eni.amel.bll.manager.impl.QuestionManagerImpl;
import fr.eni.amel.bll.manager.impl.QuestionTirageManagerImpl;
import fr.eni.amel.bll.manager.impl.TestManagerImpl;
import fr.eni.amel.bll.manager.impl.UtilisateurManagerImpl;

public class ManagerFactory {
	
	public static EpreuveManager epreuveManager() {
        return EpreuveManagerImpl.getInstance();
    }
	
	public static QuestionTirageManager questionTirageManager() {
        return QuestionTirageManagerImpl.getInstance();
    }
	
	public static QuestionManager questionManager() {
        return QuestionManagerImpl.getInstance();
    }
	
	public static UtilisateurManager utilisateurManager() {
		return UtilisateurManagerImpl.getInstance();
	}
	
	public static PromotionManager promotionManager() {
		return PromotionManagerImpl.getInstance();
	}
	public static TestManager testManager() {
		return TestManagerImpl.getInstance();
	}
	
	public static ProfilManager profilManager() {
		return ProfilManagerImpl.getInstance();
	}
	
	public static PropositionManager propositionManager() {
        return PropositionManagerImpl.getInstance();
    }
	
}
