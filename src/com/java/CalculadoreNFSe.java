package com.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class CalculadoreNFSe implements ActionListener {

    float valor_bruto, valor_liquido, cofins, csll, inss, irpj, pis, outros, iss;

    JLabel semret = new JLabel();
    JLabel semirpj = new JLabel();
    JTextField vCofins, pCofins, vCsll, pCsll, vInss, pInss, vIrpj, pIrpj, vPis, pPis, vOutros, pOutros, vISS, pISS, vnotaliq, vnotabru;
    JCheckBox issret, issnret;

    DecimalFormat dc = new DecimalFormat("###,###,##0.00");

    CalculadoreNFSe(){

        pISS = new JTextField("2,00");
        pISS.setBounds(677,25,43,25);
        pISS.setHorizontalAlignment(0);
        pISS.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    pISS.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });


        vISS = new JTextField();
        vISS.setBounds(600,25,75,25);
        vISS.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    vISS.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        JLabel labelISS = new JLabel("ISS (R$)               %");
        labelISS.setBounds(600,10,110,15);
        labelISS.setForeground(new Color(0x0b2d39));
        labelISS.setFont(new Font("ARIAL", Font.PLAIN, 12));

        issnret = new JCheckBox("Não");
        issnret.setBounds(500,45,70,15);
        issnret.setSelected(true);
        issnret.setFocusable(false);
        issnret.setOpaque(false);
        issnret.addActionListener(this);

        issret = new JCheckBox("Sim");
        issret.setBounds(500,28,70,15);
        issret.setFocusable(false);
        issret.setOpaque(false);
        issret.addActionListener(this);

        JLabel labelISSret = new JLabel("ISS Retido: ");
        labelISSret.setBounds(500,10,70,15);
        labelISSret.setForeground(new Color(0x0b2d39));
        labelISSret.setFont(new Font("ARIAL", Font.PLAIN, 12));

        JLabel labelvlservbrt = new JLabel("Valor Bruto dos Serviços (R$)");
        labelvlservbrt.setBounds(235,10,180,15);
        labelvlservbrt.setForeground(new Color(0x0b2d39));
        labelvlservbrt.setFont(new Font("ARIAL", Font.BOLD, 12));

        vnotabru = new JTextField("");
        vnotabru.setBounds(225,25, 190, 25);
        vnotabru.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    vnotabru.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });
        vnotabru.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    try {
                        valor_bruto = Float.parseFloat(vnotabru.getText().replace(',','.'));

                        cofins = Float.parseFloat(pCofins.getText().replace(',', '.'));
                        csll = Float.parseFloat(pCsll.getText().replace(',', '.'));
                        inss = Float.parseFloat(pInss.getText().replace(',', '.'));
                        irpj = Float.parseFloat(pIrpj.getText().replace(',', '.'));
                        pis = Float.parseFloat(pPis.getText().replace(',', '.'));
                        outros = Float.parseFloat(pOutros.getText().replace(',', '.'));
                        iss = Float.parseFloat(pISS.getText().replace(',', '.'));

                        if (valor_bruto * ((cofins + csll + pis) / 100) >= 10) {

                            vCofins.setText(String.valueOf(dc.format(valor_bruto * (cofins / 100))));

                            vCsll.setText(String.valueOf(dc.format(valor_bruto * (csll / 100))));

                            vInss.setText(String.valueOf(dc.format(valor_bruto * (inss / 100))));

                            if (valor_bruto * (irpj / 100) >= 10) {
                                vIrpj.setText(String.valueOf(dc.format(valor_bruto * (irpj / 100))));
                                semirpj.setText("");
                            }else {
                                vIrpj.setText(dc.format(0));
                                irpj = 0;

                                semirpj.setBounds(10,55,500,15);
                                semirpj.setForeground(new Color(0x3ba1c5));
                                semirpj.setFont(new Font("ARIAL", Font.BOLD, 12));
                                semirpj.setText("* IRPJ menor que R$10,00");
                            }

                            vPis.setText(String.valueOf(dc.format(valor_bruto * (pis / 100))));

                            vOutros.setText(String.valueOf(dc.format(valor_bruto * (outros / 100))));

                            semret.setText("");

                        }else{
                            vCofins.setText(dc.format(0));
                            vCsll.setText(dc.format(0));
                            vInss.setText(dc.format(0));
                            vIrpj.setText(dc.format(0));
                            vPis.setText(dc.format(0));
                            vOutros.setText(dc.format(0));

                            cofins = 0;
                            csll = 0;
                            inss = 0;
                            irpj = 0;
                            pis = 0;
                            outros = 0;

                            semret.setBounds(10,55,500,15);
                            semret.setForeground(new Color(0x3ba1c5));
                            semret.setFont(new Font("ARIAL", Font.BOLD, 12));
                            semret.setText("* (COFINS + CSLL + PIS) menor de R$10,00 e IRPJ menor que R$10,00");

                        }

                        vISS.setText(String.valueOf(dc.format(valor_bruto * (iss / 100))));

                        if (issret.isSelected()) {
                            vnotaliq.setText(String.valueOf(dc.format((valor_bruto * ((100 - (cofins + csll + inss + irpj + pis)) / 100)) - (valor_bruto * (iss/100)))));
                        }
                        else {
                            vnotaliq.setText(String.valueOf(dc.format(valor_bruto * ((100 - (cofins + csll + inss + irpj + pis)) / 100))));
                        }

                    }
                    catch (NumberFormatException x){
                        vnotaliq.setText(String.valueOf(dc.format(0)));

                        vCofins.setText(String.valueOf(dc.format(0)));
                        vCsll.setText(String.valueOf(dc.format(0)));
                        vInss.setText(String.valueOf(dc.format(0)));
                        vIrpj.setText(String.valueOf(dc.format(0)));
                        vPis.setText(String.valueOf(dc.format(0)));
                        vOutros.setText(String.valueOf(dc.format(0)));
                        vISS.setText(String.valueOf(dc.format(0)));

                        semirpj.setText("");
                        semret.setText("");
                    }
                }
            }
        });

        JLabel labelvlservliq = new JLabel("Valor Líquido dos Serviços (R$)");
        labelvlservliq.setBounds(20,10,180,15);
        labelvlservliq.setForeground(new Color(0x0b2d39));
        labelvlservliq.setFont(new Font("ARIAL", Font.BOLD, 12));

        vnotaliq = new JTextField();
        vnotaliq.setBounds(15,25, 190, 25);
        vnotaliq.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    vnotaliq.setEditable(true);

                    vnotabru.setText(vnotaliq.getText());
                }
                else {
                    e.consume();
                }
            }
        });
        vnotaliq.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    try {

                        valor_liquido = Float.parseFloat(vnotaliq.getText().replace(',','.'));

                        cofins = Float.parseFloat(pCofins.getText().replace(',', '.'));
                        csll = Float.parseFloat(pCsll.getText().replace(',', '.'));
                        inss = Float.parseFloat(pInss.getText().replace(',', '.'));
                        irpj = Float.parseFloat(pIrpj.getText().replace(',', '.'));
                        pis = Float.parseFloat(pPis.getText().replace(',', '.'));
                        outros = Float.parseFloat(pOutros.getText().replace(',', '.'));
                        iss = Float.parseFloat(pISS.getText().replace(',', '.'));

                        if (issnret.isSelected()) {
                            valor_bruto = (valor_liquido / ((100 - (cofins + csll + inss + irpj + pis)) / 100));
                        }
                        else {
                            if ((valor_liquido / ((100 - (cofins + csll + inss + irpj + pis + iss)) / 100)) * (irpj/100) >= 10) {
                                valor_bruto = (valor_liquido / ((100 - (cofins + csll + inss + irpj + pis + iss)) / 100));
                            }else {
                                valor_bruto = (valor_liquido / ((100 - (cofins + csll + inss + pis + iss)) / 100));
                            }
                        }

                        if (valor_bruto * ((cofins + csll + pis) / 100) >= 10) {

                            vCofins.setText(String.valueOf(dc.format(valor_bruto * (cofins / 100))));

                            vCsll.setText(String.valueOf(dc.format(valor_bruto * (csll / 100))));

                            vInss.setText(String.valueOf(dc.format(valor_bruto * (inss / 100))));

                            if (valor_bruto * (irpj / 100) >= 10) {
                                vIrpj.setText(String.valueOf(dc.format(valor_bruto * (irpj / 100))));
                                semirpj.setText("");
                            }else {
                                vIrpj.setText(dc.format(0));
                                irpj = 0;

                                semirpj.setBounds(10,55,500,15);
                                semirpj.setForeground(new Color(0x3ba1c5));
                                semirpj.setFont(new Font("ARIAL", Font.BOLD, 12));
                                semirpj.setText("* IRPJ menor que R$10,00");
                            }

                            vPis.setText(String.valueOf(dc.format(valor_bruto * (pis / 100))));

                            vOutros.setText(String.valueOf(dc.format(valor_bruto * (outros / 100))));

                            semret.setText("");

                        }else{

                            vCofins.setText(dc.format(0));
                            vCsll.setText(dc.format(0));
                            vInss.setText(dc.format(0));
                            vIrpj.setText(dc.format(0));
                            vPis.setText(dc.format(0));
                            vOutros.setText(dc.format(0));

                            cofins = 0;
                            csll = 0;
                            inss = 0;
                            irpj = 0;
                            pis = 0;
                            outros = 0;

                            if (issret.isSelected()) {
                                valor_bruto = valor_liquido / ((100 - iss) / 100);
                            }else {
                                valor_bruto = valor_liquido;
                            }

                            semret.setBounds(10,55,500,15);
                            semret.setForeground(new Color(0x3ba1c5));
                            semret.setFont(new Font("ARIAL", Font.BOLD, 12));
                            semret.setText("* (COFINS + CSLL + PIS) menor de R$10,00 e IRPJ menor que R$10,00");
                        }

                        vISS.setText(String.valueOf(dc.format(valor_bruto * (iss / 100))));

                        vnotabru.setText(String.valueOf(dc.format(valor_bruto)));

                    }
                    catch (NumberFormatException x){
                        vnotabru.setText(String.valueOf(dc.format(0)));

                        vCofins.setText(String.valueOf(dc.format(0)));
                        vCsll.setText(String.valueOf(dc.format(0)));
                        vInss.setText(String.valueOf(dc.format(0)));
                        vIrpj.setText(String.valueOf(dc.format(0)));
                        vPis.setText(String.valueOf(dc.format(0)));
                        vOutros.setText(String.valueOf(dc.format(0)));
                        vISS.setText(String.valueOf(dc.format(0)));

                        semret.setText("");
                        semirpj.setText("");
                    }
                }
            }
        });

        JPanel painel4 = new JPanel();
        painel4.setBounds(5,125,865, 75);
        painel4.setBackground(new Color(0xd3eaf2));
        painel4.setLayout(null);
        painel4.add(vnotaliq);
        painel4.add(labelvlservliq);
        painel4.add(vnotabru);
        painel4.add(labelvlservbrt);
        painel4.add(labelISSret);
        painel4.add(issret);
        painel4.add(issnret);
        painel4.add(pISS);
        painel4.add(vISS);
        painel4.add(labelISS);

        JLabel valores_da_nota = new JLabel("Valores da Nota");
        valores_da_nota.setForeground(new Color(0xffffff));
        valores_da_nota.setFont(new Font("ARIAL", Font.BOLD, 14));

        JPanel painel3 = new JPanel();
        painel3.setBounds(5,100,865, 25);
        painel3.setBackground(new Color(0x3ba1c5));
        painel3.add(valores_da_nota);


        //OUTROS

        pOutros = new JTextField("0,00");
        pOutros.setBounds(812,20,43,25);
        pOutros.setHorizontalAlignment(0);
        pOutros.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    pOutros.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        vOutros = new JTextField();
        vOutros.setBounds(735,20,75,25);
        vOutros.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    vOutros.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        JLabel labelOutros = new JLabel("OUTROS (R$)     %");
        labelOutros.setBounds(735,5,110,15);
        labelOutros.setForeground(new Color(0x0b2d39));
        labelOutros.setFont(new Font("ARIAL", Font.PLAIN, 12));

        //PIS

        pPis = new JTextField("0,65");
        pPis.setBounds(667,20,43,25);
        pPis.setHorizontalAlignment(0);
        pPis.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    pPis.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        vPis = new JTextField();
        vPis.setBounds(590,20,75,25);
        vPis.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    vPis.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        JLabel labelPis = new JLabel("PIS (R$)               %");
        labelPis.setBounds(590,5,110,15);
        labelPis.setForeground(new Color(0x0b2d39));
        labelPis.setFont(new Font("ARIAL", Font.PLAIN, 12));

        //IRPJ

        pIrpj = new JTextField("1,50");
        pIrpj.setBounds(522,20,43,25);
        pIrpj.setHorizontalAlignment(0);
        pIrpj.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    pIrpj.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        vIrpj = new JTextField();
        vIrpj.setBounds(445,20,75,25);
        vIrpj.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    vIrpj.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        JLabel labelIrpj = new JLabel("IRPJ (R$)             %");
        labelIrpj.setBounds(445,5,110,15);
        labelIrpj.setForeground(new Color(0x0b2d39));
        labelIrpj.setFont(new Font("ARIAL", Font.PLAIN, 12));

        //INSS

        pInss = new JTextField("0,00");
        pInss.setBounds(377,20,43,25);
        pInss.setHorizontalAlignment(0);
        pInss.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    pInss.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        vInss = new JTextField();
        vInss.setBounds(300,20,75,25);
        vInss.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    vInss.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        JLabel labelInss = new JLabel("INSS (R$)            %");
        labelInss.setBounds(300,5,110,15);
        labelInss.setForeground(new Color(0x0b2d39));
        labelInss.setFont(new Font("ARIAL", Font.PLAIN, 12));

        //CSLL

        pCsll = new JTextField("1,00");
        pCsll.setBounds(232,20,43,25);
        pCsll.setHorizontalAlignment(0);
        pCsll.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    pCsll.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        vCsll = new JTextField();
        vCsll.setBounds(155,20,75,25);
        vCsll.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    vCsll.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        JLabel labelCsll = new JLabel("CSLL (R$)            %");
        labelCsll.setBounds(155,5,110,15);
        labelCsll.setForeground(new Color(0x0b2d39));
        labelCsll.setFont(new Font("ARIAL", Font.PLAIN, 12));

        //COFINS

        pCofins = new JTextField("3,00");
        pCofins.setBounds(87,20,43,25);
        pCofins.setHorizontalAlignment(0);
        pCofins.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    pCofins.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        vCofins = new JTextField();
        vCofins.setBounds(10,20,75,25);
        vCofins.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || (e.getKeyChar() == ',') || ((int)e.getKeyChar() == 8)){
                    vCofins.setEditable(true);
                }
                else {
                    e.consume();
                }
            }
        });

        JLabel labelCofins = new JLabel("COFINS (R$)      %");
        labelCofins.setBounds(10,5,110,15);
        labelCofins.setForeground(new Color(0x0b2d39));
        labelCofins.setFont(new Font("ARIAL", Font.PLAIN, 12));

        JPanel painel2 = new JPanel();
        painel2.setBounds(5,25,865, 75);
        painel2.setBackground(new Color(0xd3eaf2));
        painel2.setLayout(null);
        painel2.add(labelCofins);
        painel2.add(vCofins);
        painel2.add(pCofins);
        painel2.add(labelCsll);
        painel2.add(vCsll);
        painel2.add(pCsll);
        painel2.add(labelInss);
        painel2.add(vInss);
        painel2.add(pInss);
        painel2.add(labelIrpj);
        painel2.add(vIrpj);
        painel2.add(pIrpj);
        painel2.add(labelPis);
        painel2.add(vPis);
        painel2.add(pPis);
        painel2.add(labelOutros);
        painel2.add(vOutros);
        painel2.add(pOutros);
        painel2.add(semirpj);
        painel2.add(semret);

        JLabel tributosFederais = new JLabel("Retenção de Tributos Federais");
        tributosFederais.setForeground(new Color(0xffffff));
        tributosFederais.setFont(new Font("ARIAL", Font.BOLD, 14));

        JPanel painel1 = new JPanel();
        painel1.setBounds(5,0,865, 25);
        painel1.setBackground(new Color(0x3ba1c5));
        painel1.add(tributosFederais);

        JFrame tela = new JFrame("NFSe");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null);
        tela.setSize(890,245);

        ImageIcon icoNFSe = new ImageIcon("LOGO_NFSE.gif");
        tela.setIconImage(icoNFSe.getImage());

        tela.setVisible(true);
        tela.setLocationRelativeTo(null);
        tela.setResizable(false);
        tela.add(painel1);
        tela.add(painel2);
        tela.add(painel3);
        tela.add(painel4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == issnret){
            issret.setSelected(false);
        }
        if (e.getSource() == issret){
            issnret.setSelected(false);
        }
    }
}
