import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraDoSwing extends JFrame implements ActionListener {

    private JTextField campoNumero1, campoNumero2;
    private JButton botaoSomar, botaoSubtrair, botaoMultiplicar, botaoDividir;
    private JLabel resultado;

    public CalculadoraDoSwing() {
        setTitle("Swing & Simpatia");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Tema escuro
        Color fundo = new Color(40, 40, 40);
        Color texto = new Color(220, 220, 220);
        Color botao = new Color(70, 70, 70);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBackground(fundo);
        painelPrincipal.setLayout(new GridLayout(6, 1, 10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();
        estilizarCampo(campoNumero1, fundo, texto);
        estilizarCampo(campoNumero2, fundo, texto);

        painelPrincipal.add(label("Número 1:", texto));
        painelPrincipal.add(campoNumero1);
        painelPrincipal.add(label("Número 2:", texto));
        painelPrincipal.add(campoNumero2);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 10, 10));
        painelBotoes.setBackground(fundo);

        botaoSomar = criarBotao("+", botao, texto);
        botaoSubtrair = criarBotao("-", botao, texto);
        botaoMultiplicar = criarBotao("*", botao, texto);
        botaoDividir = criarBotao("/", botao, texto);

        painelBotoes.add(botaoSomar);
        painelBotoes.add(botaoSubtrair);
        painelBotoes.add(botaoMultiplicar);
        painelBotoes.add(botaoDividir);
        painelPrincipal.add(painelBotoes);

        resultado = label("Resultado: ", texto);
        painelPrincipal.add(resultado);

        add(painelPrincipal);
    }

    private void estilizarCampo(JTextField campo, Color fundo, Color texto) {
        campo.setBackground(fundo.darker());
        campo.setForeground(texto);
        campo.setCaretColor(texto);
        campo.setBorder(BorderFactory.createLineBorder(texto));
    }

    private JButton criarBotao(String texto, Color fundo, Color corTexto) {
        JButton botao = new JButton(texto);
        botao.setBackground(fundo);
        botao.setForeground(corTexto);
        botao.setFocusPainted(false);
        botao.setFont(new Font("SansSerif", Font.BOLD, 18));
        botao.addActionListener(this);
        return botao;
    }

    private JLabel label(String texto, Color corTexto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(corTexto);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        return lbl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(campoNumero1.getText());
            double num2 = Double.parseDouble(campoNumero2.getText());
            double res = 0;

            if (e.getSource() == botaoSomar) {
                res = num1 + num2;
            } else if (e.getSource() == botaoSubtrair) {
                res = num1 - num2;
            } else if (e.getSource() == botaoMultiplicar) {
                res = num1 * num2;
            } else if (e.getSource() == botaoDividir) {
                if (num2 != 0) {
                    res = num1 / num2;
                } else {
                    resultado.setText("Erro: divisão por zero.");
                    return;
                }
            }

            resultado.setText("Resultado: " + res);
        } catch (NumberFormatException ex) {
            resultado.setText("Erro: insira números válidos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraDoSwing().setVisible(true);
        });
    }
}
