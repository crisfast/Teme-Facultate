using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Articol : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(System.Configuration.ConfigurationManager.ConnectionStrings["BlogDB"].ConnectionString);

    protected void Page_Load(object sender, EventArgs e)
    {
        if (Page.User.Identity.Name == "")
        {
            comSub.Visible = false;
            txbxcomment.Visible = false;
            
        }
        if (Request.QueryString.Get("Id") != null)
        {
            DetailsView1.DefaultMode = DetailsViewMode.ReadOnly;
            continut();
            adauga_comentarii();
        }
    }

    private void adauga_comentarii()
    {
        try
        {
            if (con.State == ConnectionState.Closed)
            {
                con.Open();
            }

            SqlCommand cmd = new SqlCommand("select * from Comentarii where PostId=@id", con);
            cmd.Parameters.AddWithValue("@id", Request.QueryString["Id"]);
            SqlDataAdapter adp = new SqlDataAdapter(cmd);
            DataSet ds = new DataSet();
            adp.Fill(ds);
            if (ds.Tables[0].Rows.Count == 0)
            {
                GridViewcomment.Visible = false;
                LabelNoComment.Visible = true;
            }
            else
            {
                GridViewcomment.Visible = true;
                LabelNoComment.Visible = false;
                GridViewcomment.DataSource = ds;
                GridViewcomment.DataBind();
            }

        }
        catch (Exception)
        {

            throw;
        }
        finally { con.Close(); }
    }

    private void continut()
    {
        try
        {
            if (con.State == ConnectionState.Closed)
            {
                con.Open();
            }

            SqlCommand cmd = new SqlCommand("select * from BlogPost where Id=@id", con);
            cmd.Parameters.AddWithValue("@id", Request.QueryString["Id"]);
            SqlDataAdapter adp = new SqlDataAdapter(cmd);
            DataSet ds = new DataSet();
            adp.Fill(ds);
            ds.Tables[0].Columns.Add("FormDate", typeof(string));
            for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
            {
                DateTime date = (DateTime)ds.Tables[0].Rows[0]["DataPost"];
                string format = "MMM ddd d, yyyy";
                ds.Tables[0].Rows[0]["FormDate"] = date.ToString(format);
            }
            DetailsView1.DataSource = ds;
            DetailsView1.DataBind();
            cmd.Dispose();
        }
        catch (Exception k)
        {
            Response.Write(k.Message);
            //throw;
        }
        finally
        {
            con.Close();
        }
    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        try
        {
            { 
                if (con.State == ConnectionState.Closed)
                {
                    con.Open();
                }

                SqlCommand cmd = new SqlCommand("insert into Comentarii(PostId, Comentariu, Nume, Data) values(@pid,@com,@nume,@data)", con);
                cmd.Parameters.AddWithValue("@pid", Request.QueryString["Id"]);
                cmd.Parameters.AddWithValue("@com", txbxcomment.Text);
                cmd.Parameters.AddWithValue("@nume", Page.User.Identity.Name);
                cmd.Parameters.AddWithValue("@data", System.DateTime.Now);
                cmd.ExecuteNonQuery();
                cmd.Dispose();
                txbxcomment.Text = String.Empty;
                adauga_comentarii();
            }

        }
        catch (Exception)
        {
            throw;
        }
        finally { con.Close(); }
    }
}