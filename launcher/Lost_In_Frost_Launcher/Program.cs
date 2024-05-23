using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Principal;
using System.Threading.Tasks;
using System.Windows.Forms;
using LostInFrost;
using System.Diagnostics;

namespace Lost_In_Frost_Launcher
{
    internal static class Program
    {
        static String tokenValue = null;

        /// <summary>
        /// 해당 애플리케이션의 주 진입점입니다.
        /// </summary>
        [STAThread]
        static void Main()
        {
            if (IsAdministrator() == false)
            {
                try
                {
                    ProcessStartInfo info = new ProcessStartInfo()
                    {
                        UseShellExecute = true,
                        FileName = Application.ExecutablePath,
                        WorkingDirectory = Environment.CurrentDirectory,
                        Verb = "runas"
                    };

                    Process.Start(info);
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                    return;
                }
            }
            else
            {
                //Application.EnableVisualStyles();
                Application.SetCompatibleTextRenderingDefault(false);
                Loading loadingForm = new Loading();
                loadingForm.TokenPassed += LoadingForm_TokenPassed;
                Application.Run(loadingForm);

                if (tokenValue != null)
                {
                    Launcher launcher = new Launcher(tokenValue);
                    Application.Run(launcher);
                }
                else
                {
                    MessageBox.Show("실패: 게임시작을 다시 눌러주세요");
                }
            }
        }

        private static void LoadingForm_TokenPassed(object sender, string token)
        {
;           tokenValue = token;
        }

        public static bool IsAdministrator()
        {
            WindowsIdentity identity = WindowsIdentity.GetCurrent();

            if (identity != null)
            {
                WindowsPrincipal principal = new WindowsPrincipal(identity);
                return principal.IsInRole(WindowsBuiltInRole.Administrator);
            }

            return false;
        }
    }
}
