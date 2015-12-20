package demo;

import demo.Card;

public class Effects
{
	public PlayGui play;


	public Effects(PlayGui play){
		this.play=play;
	}

	public void makeEffect(String id){
		System.out.println("numero de cartas en el mazo "+play.player.pdeck.Deck.cardsLeft());
		System.out.println(id);
		System.out.println("numero de cartas en el mazo "+play.player.pdeck.Deck.Consultar(0).GetName());
		System.out.println("numero de cartas en el mazo "+play.player.pdeck.Deck.Consultar(1).GetName());
		System.out.println("numero de cartas en el mazo "+play.player.pdeck.Deck.Consultar(2).GetName());
		System.out.println("numero de cartas en el mazo "+play.player.pdeck.Deck.Consultar(3).GetName());

	}




} 