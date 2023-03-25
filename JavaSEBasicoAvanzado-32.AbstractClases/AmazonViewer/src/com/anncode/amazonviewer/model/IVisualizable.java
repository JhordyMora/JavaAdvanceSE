package com.anncode.amazonviewer.model;

import java.util.Date;

public interface IVisualizable {
	/**
	 * Este método captura el tiempo exacto de vizualización
	 * @param dateI es un objeto {@code date} con el tiempo de inicio exacto.
	 * @return devuelve la fecha y la hora capturada.
	*/
	Date startToSee(Date dateI);
	/**
	 * Este método captura el tiempo exacto de inicio y final de vizualización
	 * @param dateI es un objeto {@code date} con el tiempo de inicio exacto.
	 * @param dateF es un objeto {@code date} con el tiempo de final exacto.

	 */
	void stopToSee(Date dateI, Date dateF);
	
}
