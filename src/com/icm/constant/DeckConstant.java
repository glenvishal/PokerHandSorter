package com.icm.constant;

/**
 * @author Glen
 * 
 * Contains value of cards in ascending order
 *
 */
public enum DeckConstant {
	T(10),
	J(11),
	Q(12),
	K(13),
	A(14);
	
	private final int deckValue;
	private DeckConstant(int value) {
		this.deckValue = value;
	}
	
	public int getValue() {
        return deckValue;
    }
	
	public static String getEnumName(int values){
		for(DeckConstant eachEnum: values()){
			if(eachEnum.getValue() == values){
				return eachEnum.name();
			}
		}
		return null;
	}
}
