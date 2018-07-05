using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace test
{
    public partial class Form1 : Form
    {
        private BindingSource bs = new BindingSource();
        public Form1()
        {
            InitializeComponent();
            bs.Add(new Film("hello", 1));
            bs.Add(new Film("haha", 3));
            bs.Add(new Film("zebra", 5));

            dataGridView1.AutoGenerateColumns = false;
            dataGridView1.AutoSize = true;
            dataGridView1.DataSource = bs;

            DataGridViewColumn col1 = new DataGridViewTextBoxColumn();
            col1.DataPropertyName = "FName";
            col1.Name = "Film";
            col1.ReadOnly = true;
            dataGridView1.Columns.Add(col1);

            this.Controls.Add(dataGridView1);
            this.AutoSize = true;
            this.Text = "DataGridView object binding";
        }

        private void button1_Click(object sender, EventArgs e)
        {
            bs.Add(new Film("new Film", 4));
            
        }

        private void button2_Click(object sender, EventArgs e)
        {
            foreach (DataGridViewCell oneCell in dataGridView1.SelectedCells)
            {
                if (oneCell.Selected)
                    dataGridView1.Rows.RemoveAt(oneCell.RowIndex);
            }
        }
    }
}
