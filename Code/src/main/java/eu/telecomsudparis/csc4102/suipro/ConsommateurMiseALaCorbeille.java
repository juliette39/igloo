package eu.telecomsudparis.csc4102.suipro;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * Cette classe définit le consommateur. Les publications sont typées.
 *
 */
public class ConsommateurMiseALaCorbeille implements Subscriber<Publication> {
    /**
     * alias du consommateur : pour les messages a la console qui servent a
     * suivre l'execution.
     */
    private String alias;
    /**
     * la souscription. Cet objet sert a controler le flux entre le producteur et le
     * consommateur.
     */
    private Subscription souscription;
    // on pourrait ajouter une collection pour garder les publications recues

    /**
     * constructeur.
     *
     * @param alias l'alias pour les affichages.
     */
    public ConsommateurMiseALaCorbeille(final String alias) {
        this.alias = alias;
    }

    @Override
    public void onSubscribe(final Subscription souscription) {
        this.souscription = souscription; //...
        // on consomme un message des qu'il arrive ; un a la fois
        // on declare qu'on est pret a recevoir un message
        this.souscription.request(1);
        System.out.println("Consommateur " + alias + " pret a recevoir la premiere publication");
    }

    @Override
    public void onNext(final Publication publication) {
        // reception d'une publication...
        System.out.println("Consommateur " + alias + " a recu une nouvelle publication : " + publication);
        // on declare qu'on est pret a recevoir un nouveau message
        souscription.request(1);
    }

    @Override
    public void onError(final Throwable throwable) { //...
        // erreur sur la gestion du flux, par exemple producteur.subscribe
        // d'un consommateur qui est deja un subscriber du producteur
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() { // lorsque le producteur ferme le flux
        // on affiche la fin a la console
        System.out.println("Consommateur " + alias + " est desabonne suite a la fermeture du flux par le producteur");
    }
}
