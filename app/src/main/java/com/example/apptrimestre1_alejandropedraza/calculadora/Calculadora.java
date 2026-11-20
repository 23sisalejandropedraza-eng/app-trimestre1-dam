package com.example.apptrimestre1_alejandropedraza.calculadora;

import android.util.Log;

public class Calculadora {

    private String num1;
    private String num2;
    private char operacion;
    private String mensajeError;
    private boolean resultadoMostrado;

    public Calculadora() {
        clear();
    }

    public void clear() {
        num1 = "";
        num2 = "";
        operacion = ' ';
        mensajeError = "";
        resultadoMostrado = false;
    }

    public void meterNumero(int num) {
        mensajeError = "";
        if (resultadoMostrado) {
            this.num2 = String.valueOf(num);
            resultadoMostrado = false;
        } else {
            this.num2 += num;
        }
    }

    public void meterDecimal() {
        mensajeError = "";
        if (resultadoMostrado) {
            this.num2 = "0.";
            resultadoMostrado = false;
        } else if (this.num2.isEmpty()) {
            this.num2 = "0.";
        } else if (!this.num2.contains(".")) {
            this.num2 += ".";
        }
    }

    public String getNum1() {
        return num1;
    }

    public String getNum2() {
        return num2;
    }

    public String getTextoPantalla() {
        if (!mensajeError.isEmpty()) {
            return mensajeError;
        }
        return num2;
    }

    public void operacion(char op) {
        mensajeError = "";
        if (!this.num2.isEmpty() && !this.num2.equals(".")) {
            operacion = op;
            num1 = num2;
            num2 = "";
            resultadoMostrado = false;
        }
    }

    public void igual() {
        if (this.num1.isEmpty() || this.num2.isEmpty() || operacion == ' ') {
            return;
        }
        if (this.num1.equals(".") || this.num2.equals(".")) {
            mensajeError = "Error";
            resultadoMostrado = true;
            return;
        }

        double valor1 = Double.parseDouble(this.num1);
        double valor2 = Double.parseDouble(this.num2);
        Log.d("CLASE CALC", "num1: " + valor1 + ", op: " + operacion + ", num2: " + valor2);

        double resultado = 0.0;
        switch (operacion) {
            case '+':
                resultado = valor1 + valor2;
                break;
            case '-':
                resultado = valor1 - valor2;
                break;
            case '*':
                resultado = valor1 * valor2;
                break;
            case '/':
                if (valor2 == 0.0) {
                    mensajeError = "No se puede dividir entre 0";
                    num1 = "";
                    num2 = "";
                    operacion = ' ';
                    resultadoMostrado = true;
                    return;
                }
                resultado = valor1 / valor2;
                break;
            default:
                return;
        }

        this.num2 = String.valueOf(resultado);
        this.num1 = "";
        this.operacion = ' ';
        this.resultadoMostrado = true;
    }
}