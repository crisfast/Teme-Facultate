using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Cauta : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(System.Configuration.ConfigurationManager.ConnectionStrings["BlogDB"].ConnectionString);

    protected void Page_Load(object sender, EventArgs e)
    {
       if(Request.QueryString.Get("Tip").ToString() == "Comms")
        {
            GridViewcomment.Visible = true;
            try
            {
                if (con.State == ConnectionState.Closed)
                {
                    con.Open();
                }

                SqlCommand cmd = new SqlCommand("select * from Comentarii where Nume = @nume", con);
                cmd.Parameters.AddWithValue("@nume", Request.QueryString["NumeUser"]);
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
        }

        if (Request.QueryString.Get("Tip").ToString() == "Categ")
        {
            GridView1.Visible = true;
            try
            {
                if (con.State == ConnectionState.Closed)
                {
                    con.Open();
                }

                SqlCommand cmd = new SqlCommand("select * from BlogPost where Categorie = @categ order by DataPost desc ", con);
                cmd.Parameters.AddWithValue("@categ", Request.QueryString["NumeCateg"]);
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
        }

        if (Request.QueryString.Get("Tip").ToString() == "Normal")
        {
            GridView1.Visible = true;
            try
            {
                if (con.State == ConnectionState.Closed)
                {
                    con.Open();
                }

                SqlCommand cmd = new SqlCommand("select * from BlogPost where Titlu like '%" + Request.QueryString["Cautare"].ToString() + "%' or Continut like '%" + Request.QueryString["Cautare"] + "%'", con);
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
        }

        if (Request.QueryString.Get("Tip").ToString() == "Posts")
        {
            GridView1.Visible = true;
            try
            {
                if (con.State == ConnectionState.Closed)
                {
                    con.Open();
                }

                SqlCommand cmd = new SqlCommand("select * from BlogPost where Autor = @numeautor order by DataPost desc ", con);
                cmd.Parameters.AddWithValue("@numeautor", Request.QueryString["NumeAutor"]);
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
        }

        con.Close();
    }
}