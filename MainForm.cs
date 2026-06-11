using System;
using System.Collections.Generic;
using System.Drawing;
using System.Media;
using System.Windows.Forms;

namespace CyberSafeAssistant
{
    public class MainForm : Form
    {
        private TextBox inputBox;
        private Button sendButton;
        private TextBox chatBox;

        private string userName = "";
        private string lastTopic = "";
        private Random random = new Random();

        private Dictionary<string, string[]> responses = new Dictionary<string, string[]>();

        public MainForm()
        {
            this.Text = "CyberSafe Assistant C# 🛡️";
            this.Size = new Size(700, 550);
            this.StartPosition = FormStartPosition.CenterScreen;
            this.BackColor = Color.FromArgb(20, 20, 20);

            chatBox = new TextBox();
            chatBox.Multiline = true;
            chatBox.ReadOnly = true;
            chatBox.ScrollBars = ScrollBars.Vertical;
            chatBox.Dock = DockStyle.Top;
            chatBox.Height = 400;
            chatBox.BackColor = Color.FromArgb(35, 35, 35);
            chatBox.ForeColor = Color.White;
            chatBox.Font = new Font("Consolas", 10);

            inputBox = new TextBox();
            inputBox.Dock = DockStyle.Bottom;

            sendButton = new Button();
            sendButton.Text = "Send";
            sendButton.Dock = DockStyle.Bottom;
            sendButton.Click += SendButton_Click;

            this.Controls.Add(chatBox);
            this.Controls.Add(sendButton);
            this.Controls.Add(inputBox);

            LoadResponses();

            AppendBot("Welcome to CyberSafe Assistant 🤖");
            AppendBot("What is your name?");
        }

        private void SendButton_Click(object sender, EventArgs e)
        {
            ProcessInput(inputBox.Text.Trim());
            inputBox.Clear();
        }

        private void ProcessInput(string input)
        {
            if (string.IsNullOrEmpty(input))
            {
                AppendBot("Please type something.");
                return;
            }

            AppendUser(input);

            if (userName == "")
            {
                userName = input;
                AppendBot("Nice to meet you " + userName);
                return;
            }

            if (input.ToLower() == "exit")
            {
                AppendBot("Goodbye " + userName);
                Application.Exit();
                return;
            }

            HandleConversation(input.ToLower());
        }

        private void HandleConversation(string input)
        {
            if (input.Contains("password"))
            {
                lastTopic = "password";
                AppendBot(GetRandom("password"));
                AppendBot("Use strong unique passwords.");
                return;
            }

            if (input.Contains("phishing"))
            {
                lastTopic = "phishing";
                AppendBot(GetRandom("phishing"));
                return;
            }

            if (input.Contains("malware"))
            {
                lastTopic = "malware";
                AppendBot(GetRandom("malware"));
                return;
            }

            if (input.Contains("vpn"))
            {
                lastTopic = "vpn";
                AppendBot(GetRandom("vpn"));
                return;
            }

            if (input.Contains("remember"))
            {
                AppendBot("Last topic: " + lastTopic);
                return;
            }

            AppendBot("I don't understand. Try: password, phishing, malware, vpn");
        }

        private string GetRandom(string key)
        {
            if (!responses.ContainsKey(key)) return "";
            var arr = responses[key];
            return arr[random.Next(arr.Length)];
        }

        private void LoadResponses()
        {
            responses["password"] = new string[]
            {
                "Use strong passwords with symbols.",
                "Avoid using personal info in passwords.",
                "Enable 2FA for extra security."
            };

            responses["phishing"] = new string[]
            {
                "Phishing tries to steal your data.",
                "Never click unknown links.",
                "Check sender carefully."
            };

            responses["malware"] = new string[]
            {
                "Malware harms your system.",
                "Keep antivirus updated.",
                "Avoid unknown downloads."
            };

            responses["vpn"] = new string[]
            {
                "VPN protects your privacy.",
                "Encrypts your internet traffic.",
                "Useful on public WiFi."
            };
        }

        private void AppendBot(string text)
        {
            chatBox.AppendText("BOT: " + text + Environment.NewLine + Environment.NewLine);
        }

        private void AppendUser(string text)
        {
            chatBox.AppendText(userName + ": " + text + Environment.NewLine + Environment.NewLine);
        }
    }
}
