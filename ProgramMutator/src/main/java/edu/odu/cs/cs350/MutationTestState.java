package edu.odu.cs.cs350;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import com.google.gson.Gson;

class MutationTestState {

	List<Mutant> alive;
	List<Mutant> dead;
	List<Mutant> allMutants;
	Gson state;
	
	MutationTestState() //create blank mutation test state object
	{
		this.alive = new ArrayList<Mutant> ();
		this.dead = new ArrayList<Mutant> ();
		this.allMutants = new ArrayList<Mutant> ();
		this.state = new Gson();
	}
	Mutant getMutantByID(int mID)
	{
		int mIndex = 0;
		
	//iterate through the list of mutants and attempt to match the int ID,
	//then set mIndex to the index of the located mutant
		for(Mutant currentMutant : this.allMutants)
		{
			if (currentMutant.id == mID)
			{
				mIndex = allMutants.indexOf(currentMutant);
			}
		}
		//return the mutant object located by the search
		return allMutants.get(mIndex);
	}
	
	void killMutant(Mutant m)
	{
		m.setAlive(false); 
		//add to dead collection
		if (this.dead.contains(m) == false)
		{
			this.dead.add(m);
		}
		//remove from alive collection
		if (this.alive.contains(m))
		{
			this.alive.remove(m);
		}
	}
	
	void printLiveMutants()
	{
		for(Mutant currentMutant : this.alive)
		{
			System.out.println(currentMutant.toString());
		}
	}
	//prints the rate at which mutants have been killed
	void printSuccessRate()
	{
		int liveMuts = this.alive.size();
		int allMuts = this.allMutants.size();
		double rate = 1 - (liveMuts/allMuts);
		System.out.println("Success Rate:\n" + liveMuts + " of " + allMuts + " are still alive."
				+ "\nThe given tests only successfully removed " + rate + "% of the viable mutants.");
	}
	//generate mutation test state output as gson, will need to call toJson(this object) to convert for json output
	Gson getTestState()
	{
		return this.state;
	}
	
	boolean store(String dirPath) {
		File path = new File(dirPath);
		path.mkdirs();
		try (FileWriter output = new FileWriter(dirPath + "/mutation.json")) {
			//do stuff
			output.close();
			return true;
		} catch (IOException e) {
			System.err.println(e);
		}
		return false;
	}
	
}

