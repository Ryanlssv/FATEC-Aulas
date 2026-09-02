using MauiAppAula4.Mod;

namespace MauiAppAula4
{
    public partial class MainPage : ContentPage
    {

        public MainPage()
        {
            InitializeComponent();
        }

        private void btnSoma_Clicked(object sender, EventArgs e)
        {
            CalcularParam("+");
        }

        private void btnSub_Clicked(object sender, EventArgs e)
        {
            CalcularParam("-");
        }

        private void btnMult_Clicked(object sender, EventArgs e)
        {
            CalcularParam("*");
        }

        private void btnDiv_Clicked(object sender, EventArgs e)
        {
            CalcularParam("/");
        }

        private async Task ClearForm()
        {
            txtNum1.Text = string.Empty;
            txtNum2.Text = string.Empty;
            await Task.Delay(50);

            txtNum1.Focus();
        }

        private void setForm(DoisParametro dp, string operacao)
        {
            string sinal = "";

            // Escolhe o método correto baseado no botão clicado
            if (operacao == "+") { dp.CalcularSoma(); sinal = "+"; }
            else if (operacao == "-") { dp.CalcularSub(); sinal = "-"; }
            else if (operacao == "*") { dp.CalcularMult(); sinal = "x"; }
            else if (operacao == "/") { dp.CalcularDiv(); sinal = "/"; }

            // Exibe o resultado na tela
            lblRes.Text = $"A operação {dp.gsN1} {sinal} {dp.gsN2} = {dp.gsS:0.00}";
        }

        private DoisParametro getForm()
        {
            DoisParametro dp = new DoisParametro();
            dp.gsN1 = Convert.ToInt32(txtNum1.Text);
            dp.gsN2 = Convert.ToInt32(txtNum2.Text);
            return dp;
        }

        private async void CalcularParam(string operacao)
        {
            if (ValidarForm())
            {
                DoisParametro dp = getForm();
                setForm(dp, operacao);
                await ClearForm();
            }
        }

        private bool ValidarForm()
        {
            string num1 = txtNum1.Text;
            if (num1 == string.Empty || num1 == null) 
            {
                lblRes.Text = "Campo número 1 obrigatório ";
                txtNum1.Focus();
                return false;
            }
            else
            {
                string num2 = txtNum2.Text;
                if (num2 == string.Empty || num2 == null)
                {
                    lblRes.Text = "Campo número 2 obrigatório ";
                    txtNum2.Focus();
                    return false;
                }
                else
                {
                    lblRes.Text = string.Empty;
                    return true;
                }
            }
        }
    }
}
