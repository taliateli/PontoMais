package com.taliateli.util;

import java.lang.reflect.Field;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class Util {

	public static String removeAcentos(String str) {

		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;

	}

	public static String getDataFormatada(Date data, String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		if (data != null) {
			return sdf.format(data);
		}
		return "";
	}

	public static Date formataData(String data, String formato) {
		try {
			return new SimpleDateFormat(formato).parse(data);
		} catch (ParseException ex) {
			return null;
		}
	}

	public static Date getData(String data) {
		return formataData(data, "dd/MM/yyyy");
	}

	public static String getString(Object[] vetor) {
		StringBuilder sb = new StringBuilder();
		for (Object obj : vetor) {
			sb.append(obj).append(",");
		}
		return sb.substring(0, sb.length() - 1).trim();
	}

	public static Boolean[] getVetorBoolean(String vetorStr) {
		String[] vetor = vetorStr.split(",");
		Boolean[] vetorBoolean = new Boolean[vetor.length];
		for (int i = 0; i < vetor.length; i++) {
			vetorBoolean[i] = Boolean.parseBoolean(vetor[i]);
		}
		return vetorBoolean;
	}

	public static Integer[] getVetorInt(String dados) {
		try {
			if (dados != null) {
				String[] vetor = dados.trim().split(",");
				Integer[] vetorInt = new Integer[vetor.length];
				for (int i = 0; i < vetor.length; i++) {
					vetorInt[i] = new Integer(vetor[i]);
				}
				return vetorInt;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> getAtributos(Class<?> clazz)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		List<String> valores = new ArrayList<String>();
		Field[] campos = clazz.getFields();
		for (Field f : campos) {
			valores.add(f.get(clazz.newInstance()).toString());
		}
		return valores;
	}

	public static Boolean isEmpty(Collection lista) {
		if (lista == null) {
			return true;
		}
		if (lista.isEmpty()) {
			return true;
		}
		return false;
	}

	public static Boolean isEmpty(Map lista) {
		if (lista == null) {
			return true;
		}
		if (lista.isEmpty()) {
			return true;
		}
		return false;
	}

	public static Double inverterSinal(Double valor) {
		return valor *= -1;
	}

	public static String acrescentarZerosEsquerda(String input, int width, char ch) {
		String strPad = "";

		StringBuilder sb = new StringBuilder(input.trim());
		while (sb.length() < width) {
			sb.insert(0, ch);
		}
		strPad = sb.toString();

		if (strPad.length() > width) {
			strPad = strPad.substring(0, width);
		}
		return strPad;
	}

	public static String formataNumero(double valor, int inteiros, int decimal, boolean grupo) {
		NumberFormat nf = NumberFormat.getIntegerInstance();
		nf.setMinimumIntegerDigits(inteiros);
		nf.setMinimumFractionDigits(decimal);
		nf.setMaximumFractionDigits(decimal);
		nf.setGroupingUsed(grupo);
		return nf.format(valor);
	}

	public static void dateField(final TextField textField) {
		maxField(textField, 10);

		textField.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() < 11) {
					String value = textField.getText();
					value = value.replaceAll("[^0-9]", "");
					value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
					value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
					textField.setText(value);
					positionCaret(textField);
				}
			}
		});
	}

	private static void maxField(final TextField textField, final Integer length) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				if (newValue.length() > length)
					textField.setText(oldValue);
			}
		});
	}

	private static void positionCaret(final TextField textField) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Posiciona o cursor sempre a direita.
				textField.positionCaret(textField.getText().length());
			}
		});
	}

}
