namespace Lost_In_Frost_Launcher
{
    partial class Loading
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
<<<<<<< HEAD
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Loading));
=======
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
            this.Text = new MaterialSkin.Controls.MaterialLabel();
            this.SuspendLayout();
            // 
            // Text
            // 
            this.Text.AutoSize = true;
            this.Text.Depth = 0;
            this.Text.Font = new System.Drawing.Font("Roboto", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel);
            this.Text.ForeColor = System.Drawing.Color.White;
<<<<<<< HEAD
            this.Text.Location = new System.Drawing.Point(120, 60);
=======
            this.Text.Location = new System.Drawing.Point(105, 48);
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
            this.Text.MouseState = MaterialSkin.MouseState.HOVER;
            this.Text.Name = "Text";
            this.Text.Size = new System.Drawing.Size(68, 19);
            this.Text.TabIndex = 0;
            this.Text.Text = "LOADING";
            // 
            // Loading
            // 
<<<<<<< HEAD
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(325, 139);
            this.Controls.Add(this.Text);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
=======
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(284, 111);
            this.Controls.Add(this.Text);
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
            this.Name = "Loading";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private MaterialSkin.Controls.MaterialLabel Text;
    }
}