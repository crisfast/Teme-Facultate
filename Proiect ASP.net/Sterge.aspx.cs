using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Sterge : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(System.Configuration.ConfigurationManager.ConnectionStrings["BlogDB"].ConnectionString);

    protected void Page_Load(object sender, EventArgs e)
    {
        var roluri = Roles.GetRolesForUser();
        if (roluri.Contains("Standard") || !User.Identity.IsAuthenticated || roluri.Contains("Editor"))
        {
            Response.Redirect("Default.aspx");
            return;
        }

        if (!Page.IsPostBack)
        {
            try
            {
                if (con.State == ConnectionState.Closed)
                {
                    con.Open();
                }

                SqlCommand cmd = new SqlCommand("select * from BlogPost where Id=@id", con);
                cmd.Parameters.AddWithValue("@id", Request.QueryString["Id"]);
                var reader = cmd.ExecuteReader();
                if (reader.Read())
                {
                    titluLabel.Text = reader["Titlu"].ToString();
                    autorLabel.Text = reader["Autor"].ToString();
                }
            }
            catch (Exception)
            {

                throw;
            }

        }
    }

    protected void Button1_Click(object sender, EventArgs e)
    {

        try
        {
            if (con.State == ConnectionState.Closed)
            {
                con.Open();
            }

            SqlCommand cmd0 = new SqlCommand("select * from BlogPost where Id=@id", con);
            cmd0.Parameters.AddWithValue("@id", Request.QueryString["Id"]);
            var reader = cmd0.ExecuteReader();
            if (reader.Read())
            {
                string PathPoza = Server.MapPath("~/Images/" + reader["NumePoza"].ToString() + ".jpg");
                if (System.IO.File.Exists(PathPoza))
                {
                    System.IO.File.Delete(PathPoza);
                }
                reader.Close();
            }

            SqlCommand cmd = new SqlCommand("Delete from Comentarii where PostId=@id", con);
            cmd.Parameters.AddWithValue("@id", Request.QueryString["Id"]);
            cmd.ExecuteNonQuery();

            SqlCommand cmd2 = new SqlCommand("Delete from BlogPost where Id=@id", con);
            cmd2.Parameters.AddWithValue("@id", Request.QueryString["Id"]);
            cmd2.ExecuteNonQuery();

            Response.Redirect("Default.aspx");
        }
        catch (Exception)
        {
            throw;
        }
        finally { con.Close(); }

    }

    protected void Button2_Click(object sender, EventArgs e)
    {
        Response.Redirect("Default.aspx");
    }
}