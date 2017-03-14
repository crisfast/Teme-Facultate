using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;
using System.Web.Security;

public partial class Editare : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(System.Configuration.ConfigurationManager.ConnectionStrings["BlogDB"].ConnectionString);

    protected void Page_Load(object sender, EventArgs e)
    {
        var roluri = Roles.GetRolesForUser();
        if (roluri.Contains("Standard") || !User.Identity.IsAuthenticated)
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
                    titluTB.Text = reader["Titlu"].ToString();
                    mesajTB.Text = reader["Continut"].ToString();
                    pozaAct.ImageUrl = "Images/" + reader["NumePoza"].ToString() + ".jpg";
                    categSelect.SelectedValue = reader["Categorie"].ToString();
                }
                reader.Close();
            }
            catch (Exception)
            {

                throw;
            }
        }
    }

    protected void butonTrimite_Click(object sender, EventArgs e)
    {
        try
        {
            if (con.State == ConnectionState.Closed)
            {
                con.Open();
            }

            if (FileUpload1.HasFile)
            {
                if (FileUpload1.PostedFile.ContentType.ToLower().EndsWith("jpeg"))
                {
                    String poza = DateTime.Now.Ticks.ToString();

                    SqlCommand command = new SqlCommand(@"Update BlogPost SET titlu = @titlu, continut = @continut, NumePoza = @poza, Categorie = @categ where Id = @id", con);
                    command.Parameters.AddWithValue("@id", Request.QueryString["Id"]);
                    command.Parameters.AddWithValue("@titlu", titluTB.Text);
                    command.Parameters.AddWithValue("@continut", mesajTB.Text);
                    command.Parameters.AddWithValue("@poza", poza);
                    command.Parameters.AddWithValue("@categ", categSelect.Text);

                    SqlCommand cmd = new SqlCommand("select * from BlogPost where Id=@id", con);
                    cmd.Parameters.AddWithValue("@id", Request.QueryString["Id"]);
                    var reader = cmd.ExecuteReader();
                    if (reader.Read())
                    {
                        string PathPoza = Server.MapPath("~/Images/" + reader["NumePoza"].ToString() + ".jpg");
                        if (System.IO.File.Exists(PathPoza))
                        {
                            System.IO.File.Delete(PathPoza);
                        }
                        reader.Close();
                    }
                    FileUpload1.SaveAs(Server.MapPath("~") + "/Images/" + poza + ".jpg");

                    command.ExecuteNonQuery();
                    Response.Redirect("Default.aspx");
                    con.Close();
                }
            }
            else
            {
                SqlCommand command = new SqlCommand(@"Update BlogPost SET titlu = @titlu, continut = @continut, Categorie = @categ where Id = @id", con);
                command.Parameters.AddWithValue("@id", Request.QueryString["Id"]);
                command.Parameters.AddWithValue("@titlu", titluTB.Text);
                command.Parameters.AddWithValue("@continut", mesajTB.Text);
                command.Parameters.AddWithValue("@categ", categSelect.Text);

                command.ExecuteNonQuery();
                Response.Redirect("Default.aspx");
                con.Close();
            }
        }
        catch (Exception ex)
        {
            Response.Write("Eroare: " + ex.ToString());
            return;
        }
    }
}
