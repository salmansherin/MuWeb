from pydriller import RepositoryMining
from pydriller import GitRepository
import pydriller


def main():
        urls = ["https://github.com/ishepard/pydriller", "https://github.com/hsmp89/google-selenium-automation-pom","https://github.com/apache/hadoop.git"]
        try:
            for commit in RepositoryMining(path_to_repo=urls).traverse_commits():
                print("Project {}, commit {}, date {}".format(commit.project_path, commit.hash, commit.author_date))
        except:
            print("Cannot delete file error")

if __name__ == '__main__':
    main()