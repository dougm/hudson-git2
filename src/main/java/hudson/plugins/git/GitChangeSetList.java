package hudson.plugins.git;

import hudson.model.AbstractBuild;
import hudson.scm.ChangeLogSet;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * List of changeset that went into a particular build.
 *
 * @author Nigel Magnay
 */
public class GitChangeSetList extends ChangeLogSet<GitChangeSet> {

	private final List<GitChangeSet> changeSets;

	/* package */GitChangeSetList(AbstractBuild build, List<GitChangeSet> logs) {
		super(build);
		Collections.reverse(logs); // put new things first
		this.changeSets = Collections.unmodifiableList(logs);
		for (GitChangeSet log : logs) {
			log.setParent(this);
		}
	}

	public boolean isEmptySet() {
		return this.changeSets.isEmpty();
	}

	public Iterator<GitChangeSet> iterator() {
		return this.changeSets.iterator();
	}

	public List<GitChangeSet> getLogs() {
		return this.changeSets;
	}

}
