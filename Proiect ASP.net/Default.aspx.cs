using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(System.Configuration.ConfigurationManager.ConnectionStrings["BlogDB"].ConnectionString);

    protected void Page_Load(object sender, EventArgs e)
    {
        BindBlog();
    }

    protected string Limit(object conti, int max)
    {
        var continut = (string)conti;
        if (string.IsNullOrEmpty(continut)) { return continut; }
        return continut.Length <= max ?
            continut : continut.Substring(0, max) + "...";
    }

    protected bool SetVisibility(object conti, int maxLength)
    {
        var continut = (string)conti;
        if (string.IsNullOrEmpty(continut)) { return false; }
        return continut.Length > maxLength;
    }

    protected void ReadMoreLinkButton_Click(object sender, EventArgs e)
    {
       
    }

    private void BindBlog()
    {
        try
        {
            if (con.State == ConnectionState.Closed)
            {
                con.Open();
            }

            SqlCommand cmd = new SqlCommand("select * from BlogPost order by DataPost desc ", con);
            SqlDataAdapter adp = new SqlDataAdapter(cmd);
            DataSet ds = new DataSet();
            adp.Fill(ds);
            GridView1.DataSource = ds;
            GridView1.DataBind();
            cmd.Dispose();
        }
        catch (Exception k)
        {
            Response.Write(k.Message);
        }
        finally
        {
            con.Close();
        }
    }
}