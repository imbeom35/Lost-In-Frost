namespace LostInFrost
{
    partial class Launcher
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마세요.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Launcher));
            this.btn_install = new MaterialSkin.Controls.MaterialButton();
            this.btn_start = new MaterialSkin.Controls.MaterialButton();
            this.progressBar = new MaterialSkin.Controls.MaterialProgressBar();
            this.Label = new MaterialSkin.Controls.MaterialLabel();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // btn_install
            // 
            this.btn_install.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
            this.btn_install.Density = MaterialSkin.Controls.MaterialButton.MaterialButtonDensity.Default;
            this.btn_install.Depth = 0;
            this.btn_install.HighEmphasis = true;
            this.btn_install.Icon = null;
<<<<<<< HEAD
            this.btn_install.Location = new System.Drawing.Point(477, 388);
            this.btn_install.Margin = new System.Windows.Forms.Padding(5, 8, 5, 8);
=======
            this.btn_install.Location = new System.Drawing.Point(417, 310);
            this.btn_install.Margin = new System.Windows.Forms.Padding(4, 6, 4, 6);
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
            this.btn_install.MouseState = MaterialSkin.MouseState.HOVER;
            this.btn_install.Name = "btn_install";
            this.btn_install.NoAccentTextColor = System.Drawing.Color.Empty;
            this.btn_install.Size = new System.Drawing.Size(79, 36);
            this.btn_install.TabIndex = 4;
            this.btn_install.Text = "Install";
            this.btn_install.Type = MaterialSkin.Controls.MaterialButton.MaterialButtonType.Contained;
            this.btn_install.UseAccentColor = false;
            this.btn_install.UseVisualStyleBackColor = true;
            // 
            // btn_start
            // 
            this.btn_start.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
            this.btn_start.Density = MaterialSkin.Controls.MaterialButton.MaterialButtonDensity.Default;
            this.btn_start.Depth = 0;
            this.btn_start.HighEmphasis = true;
            this.btn_start.Icon = null;
<<<<<<< HEAD
            this.btn_start.Location = new System.Drawing.Point(576, 388);
            this.btn_start.Margin = new System.Windows.Forms.Padding(5, 8, 5, 8);
=======
            this.btn_start.Location = new System.Drawing.Point(504, 310);
            this.btn_start.Margin = new System.Windows.Forms.Padding(4, 6, 4, 6);
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
            this.btn_start.MouseState = MaterialSkin.MouseState.HOVER;
            this.btn_start.Name = "btn_start";
            this.btn_start.NoAccentTextColor = System.Drawing.Color.Empty;
            this.btn_start.Size = new System.Drawing.Size(67, 36);
            this.btn_start.TabIndex = 5;
            this.btn_start.Text = "START";
            this.btn_start.Type = MaterialSkin.Controls.MaterialButton.MaterialButtonType.Contained;
            this.btn_start.UseAccentColor = false;
            this.btn_start.UseVisualStyleBackColor = true;
            // 
            // progressBar
            // 
            this.progressBar.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right)));
            this.progressBar.Depth = 0;
<<<<<<< HEAD
            this.progressBar.Location = new System.Drawing.Point(0, 374);
            this.progressBar.Margin = new System.Windows.Forms.Padding(0);
            this.progressBar.MouseState = MaterialSkin.MouseState.HOVER;
            this.progressBar.Name = "progressBar";
            this.progressBar.Size = new System.Drawing.Size(667, 5);
=======
            this.progressBar.Location = new System.Drawing.Point(0, 299);
            this.progressBar.Margin = new System.Windows.Forms.Padding(0);
            this.progressBar.MouseState = MaterialSkin.MouseState.HOVER;
            this.progressBar.Name = "progressBar";
            this.progressBar.Size = new System.Drawing.Size(584, 5);
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
            this.progressBar.Style = System.Windows.Forms.ProgressBarStyle.Continuous;
            this.progressBar.TabIndex = 6;
            // 
            // Label
            // 
            this.Label.AutoSize = true;
            this.Label.Depth = 0;
            this.Label.Font = new System.Drawing.Font("Roboto", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel);
            this.Label.ForeColor = System.Drawing.Color.White;
<<<<<<< HEAD
            this.Label.Location = new System.Drawing.Point(14, 399);
=======
            this.Label.Location = new System.Drawing.Point(12, 319);
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
            this.Label.MouseState = MaterialSkin.MouseState.HOVER;
            this.Label.Name = "Label";
            this.Label.Size = new System.Drawing.Size(107, 19);
            this.Label.TabIndex = 7;
            this.Label.Text = "materialLabel1";
            // 
            // pictureBox1
            // 
            this.pictureBox1.Dock = System.Windows.Forms.DockStyle.Top;
            this.pictureBox1.Image = ((System.Drawing.Image)(resources.GetObject("pictureBox1.Image")));
            this.pictureBox1.Location = new System.Drawing.Point(0, 0);
            this.pictureBox1.Margin = new System.Windows.Forms.Padding(0);
            this.pictureBox1.Name = "pictureBox1";
<<<<<<< HEAD
            this.pictureBox1.Size = new System.Drawing.Size(667, 374);
=======
            this.pictureBox1.Size = new System.Drawing.Size(584, 299);
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
            this.pictureBox1.TabIndex = 8;
            this.pictureBox1.TabStop = false;
            // 
            // Launcher
            // 
<<<<<<< HEAD
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(667, 451);
=======
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(584, 361);
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.Label);
            this.Controls.Add(this.progressBar);
            this.Controls.Add(this.btn_start);
            this.Controls.Add(this.btn_install);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
<<<<<<< HEAD
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
=======
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626
            this.Name = "Launcher";
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private MaterialSkin.Controls.MaterialButton btn_install;
        private MaterialSkin.Controls.MaterialButton btn_start;
        private MaterialSkin.Controls.MaterialLabel Label;
        private System.Windows.Forms.PictureBox pictureBox1;
        private MaterialSkin.Controls.MaterialProgressBar progressBar;
    }
}
